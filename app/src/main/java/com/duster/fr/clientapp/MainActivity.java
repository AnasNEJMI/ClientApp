package com.duster.fr.clientapp;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class MainActivity extends Activity{


    TextView appIsConnected, result;
    EditText editcolor, editsize, editshape;
    Button btnPost, btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (TextView) findViewById(R.id.result);
        editcolor = (EditText) findViewById(R.id.editcolor);
        editsize = (EditText) findViewById(R.id.editsize);
        editshape = (EditText) findViewById(R.id.editshape);
        btnPost = (Button) findViewById(R.id.btnPost);


        final MagicBall magic= new MagicBall();

        magic.setColor(editcolor.getText().toString());
        magic.setSize(editsize.getText().toString());
        magic.setShape(editshape.getText().toString());

        //Set the background of "MagicBall" to green is connected

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId())
                {
                    case R.id.btnPost:
                        if(!validate())
                            Toast.makeText(getBaseContext(),"Enter the color, the size and the shape please",Toast.LENGTH_SHORT).show();

                            String r = POST(magic);
                            result.setText(r);
                        break;

                }

            }
        });

    }

    public static String POST( MagicBall magicBall) {
        InputStream inputStream ;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            // make POST request to the given URL
            HttpPost httpPost = new HttpPost("http://localhost:8080/magicball/post");

            String json = "";

            // build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("color", magicBall.getColor());
            jsonObject.accumulate("size", magicBall.getSize());
            jsonObject.accumulate("shape", magicBall.getShape());

            json = jsonObject.toString();
            StringEntity se = new StringEntity(json);

            httpPost.setEntity(se);

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");


            HttpResponse httpResponse = httpclient.execute(httpPost);
            inputStream = httpResponse.getEntity().getContent();
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = " It did not work!";






        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  return result

        return result;
    }



    private boolean validate(){
        if(editcolor.getText().toString().trim().equals(""))
            return false;
        else if(editsize.getText().toString().trim().equals(""))
            return false;
        else if(editshape.getText().toString().trim().equals(""))
            return false;
        else
            return true;
    }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }


}
