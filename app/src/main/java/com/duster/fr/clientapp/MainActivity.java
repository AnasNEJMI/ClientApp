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

public class MainActivity extends Activity implements View.OnClickListener{


    TextView appIsConnected, result;
    EditText editcolor, editsize, editshape;
    Button btnPost;


    MagicBall magicBall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appIsConnected = (TextView) findViewById(R.id.appIsConnected);
        result = (TextView) findViewById(R.id.result);
        editcolor = (EditText) findViewById(R.id.editcolor);
        editsize = (EditText) findViewById(R.id.editsize);
        editshape = (EditText) findViewById(R.id.editshape);
        btnPost = (Button) findViewById(R.id.btnPost);

        if (appIsConnected()) appIsConnected.setBackgroundColor(0xFF23B571);

        btnPost.setOnClickListener((View.OnClickListener) this);
    }

    public static String POST( MagicBall magicBall) {
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            String url = new String("localhost:8080/hello/anas");
            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            String json = "";

            // 3. build jsonObject
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



        // 11. return result


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean appIsConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(MainActivity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnPost:
                if (!validate())
                    Toast.makeText(getBaseContext(), "Enter the color, size and shape", Toast.LENGTH_LONG).show();
                new HttpAsyncTask().execute("http://hmkcode.appspot.com/jsonservlet");
                break;
        }
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            magicBall = new MagicBall();
            magicBall.setColor(editcolor.getText().toString());
            magicBall.setSize(editsize.getText().toString());
            magicBall.setShape(editshape.getText().toString());

            return POST(urls[0],magicBall);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            if(!result.equals("")){
            Toast.makeText(getBaseContext(), "Data is sent properly", Toast.LENGTH_LONG).show();}
        }
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
