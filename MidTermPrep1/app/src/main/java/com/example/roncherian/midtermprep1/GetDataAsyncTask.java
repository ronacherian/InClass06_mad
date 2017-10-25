package com.example.roncherian.midtermprep1;

import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roncherian on 15/10/17.
 */

public class GetDataAsyncTask  extends AsyncTask<List<String>, Integer, ArrayList<ApiFeedModel>> {

    IApiFeeds iApiFeeds;

    public GetDataAsyncTask(IApiFeeds iApiFeeds) {
        this.iApiFeeds = iApiFeeds;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<ApiFeedModel> apiFeedModels) {

        iApiFeeds.showResults(apiFeedModels);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected ArrayList<ApiFeedModel> doInBackground(List<String>... lists) {


        /*List<String> recipeList = lists[0];
        String dish = recipeList.get(0);
        StringBuilder ingredients = new StringBuilder();
        recipeList.remove(0);
        for (String recipe : recipeList) {
            if (ingredients.length() != 0)
                ingredients.append(",").append(recipe);
            else {
                ingredients.append(recipe);
            }
        }
        Log.d("demoFound", ingredients.toString() + dish);*/


        RequestParams requestParams = new RequestParams("GET", "https://itunes.apple.com/us/rss/topgrossingapplications/limit=100/xml");
        /*requestParams.addParam("format", "xml");
        requestParams.addParam("q", dish);
        requestParams.addParam("i", ingredients.toString());*/


        HttpURLConnection connection = (HttpURLConnection) requestParams.setupConnection();
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {


            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while (null != (line = bufferedReader.readLine())) {
                    //publishProgress(count++);
                    stringBuilder.append(line);
                }

                Log.d("demo", stringBuilder.toString());

                return AppsActivityUtil.PersonsPullParser.parseRecipes(stringBuilder.toString());

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


        return null;
    }

    public  interface IApiFeeds
    {
        void showResults(ArrayList<ApiFeedModel> results);
        void startProgressbar();
    }
}
