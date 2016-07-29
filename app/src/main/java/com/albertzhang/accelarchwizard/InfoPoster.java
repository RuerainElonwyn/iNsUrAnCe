package com.albertzhang.accelarchwizard;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Orcas on 29/7/2016.
 */
public class InfoPoster extends AsyncTask<String, String, String> {
    private static final String DATA_URL = "http://sharksure-kashie.rhcloud.com/data/";
    private String[] data;
    public InfoPoster(){

    }
    @Override
    public void onPreExecute(){
        super.onPreExecute();
    }
    @Override
    public String doInBackground(String... params){
        String user = params[0];
        String time = params[1];
        String type = params[2];
        String value = params[3];
        String urlString = DATA_URL + user + "/" + time + "/" + type + "/" + value;
        try{
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void onPostExecute(String result){
        super.onPostExecute(result);
    }
}
