package com.duster.fr.clientapp;

import android.net.http.AndroidHttpClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Anas on 18/08/2015.
 */
public class httpManager {

    public static String getData(String uri) throws IOException {
        AndroidHttpClient client = AndroidHttpClient.newInstance("Android");
        HttpGet request =new HttpGet(uri);
        HttpResponse response;


            response = client.execute(request);
            return EntityUtils.toString(response.getEntity());


    }
}
