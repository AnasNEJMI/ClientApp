package com.duster.fr.clientapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Anas on 18/08/2015.
 */
public class MagicBallParser {
    public  MagicBall parseFeed(String content) {


        JSONObject obj = null;
        try {
            obj = new JSONObject(content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MagicBall magic = new MagicBall();

        try {
            magic.setColor(obj.getString("color"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            magic.setSize(obj.getString("size"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            magic.setColor(obj.getString("shape"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return magic;
    }




}

