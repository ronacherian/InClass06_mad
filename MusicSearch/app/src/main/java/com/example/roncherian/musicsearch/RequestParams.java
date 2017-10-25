package com.example.roncherian.musicsearch;
//Ron Abraham Cherian - 801028678
//Arun Thomas Kunnumpuram
import android.util.Log;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by roncherian on 07/10/17.
 */

public class RequestParams {


    String method, baseURL;

    HashMap<String ,String> params = new HashMap<String, String>();

    public RequestParams(String method, String url) {
        this.method = method;
        this.baseURL = url;
    }

    public  void addParam(String key, String value){
        params.put(key,value);
    }

    public String getEncodedParams(){

        StringBuilder stringBuilder = new StringBuilder();
        for (String key: params.keySet()){
            try {
                String value = URLEncoder.encode(params.get(key), "UTF-8");
                if (stringBuilder.length() > 0){
                    stringBuilder.append("&");
                }
                stringBuilder.append(key+"="+value);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    public String getEncodedURL(){
        Log.d("demoFound",this.baseURL + "?" + getEncodedParams());
        return this.baseURL + "?" + getEncodedParams();
    }

    public HttpURLConnection setupConnection(){
        if (method.equals("GET")){
            try {
                URL url = new URL(getEncodedURL());
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                return  connection;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if(method.equals("POST")){
            HttpURLConnection connection = null;
            String result = null;

            URL url = null;
            try {
                url = new URL("http://api.theappsdr.com/params.php");

            connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                String encodedParams = "param1=" + URLEncoder.encode("value 1", "UTF-8") + "&" +
                        "param1=" + URLEncoder.encode("value 2", "UTF-8") + "&" +
                        "param1=" + URLEncoder.encode("value 3", "UTF-8");
                writer.write(encodedParams);
                writer.flush();
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    result = IOUtils.toString(connection.getInputStream(), "UTF8");
                }
                /*connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    result = IOUtils.toString(connection.getInputStream(), "UTF8");
                }*/
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return connection;

        }
        return null;
    }
}