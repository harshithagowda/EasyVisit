package com.wireless.cse5345.easyvisit;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Harshitha Gowda on 6/27/2016.
 */
public abstract class RestParser {

    public static JSONArray getResponseForUrl(String url, String requestMethod) {

        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL urlToOpen = new URL(url);
            urlConnection = (HttpURLConnection) urlToOpen.openConnection();

            /** Set Request Properties **/
            urlConnection.setRequestProperty("Content-Type","application/json");
            //urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");


            /** Set Method **/
            urlConnection.setRequestMethod(requestMethod);
            int statusCode = urlConnection.getResponseCode();
            System.out.println("Printing the statis code:--------"+statusCode);


            if(statusCode == 200){
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
                return new JSONArray(getResponseText(inputStream));
            }


        } catch (Exception e){
            Log.d("getResponseForUrl", e.getLocalizedMessage());
        }
        return  null;
    }

    private static String getResponseText(InputStream inputStream){
        return new Scanner(inputStream).useDelimiter("\\A").next();
    }



}

