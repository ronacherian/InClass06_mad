package com.example.roncherian.recipesxml;
//Class Assignemnt: 6
//Ron Abraham Cherian
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roncherian on 02/10/17.
 */

public class GetDataAsyncTask extends AsyncTask<List<String>, Integer, List<Recipe>> {

    MainActivity activity;

    Integer count = 0;

    public GetDataAsyncTask(MainActivity activity) {
        this.activity = activity;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        activity.progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onPostExecute(List<Recipe> recipes) {
        super.onPostExecute(recipes);

        ArrayList<Recipe> newRecipeList = new ArrayList<Recipe>();
        newRecipeList = (ArrayList<Recipe>) recipes;

        activity.progressBar.setVisibility(View.INVISIBLE);

        if (newRecipeList.size() == 0) {
            //activity.dialog.dismiss();
            Toast.makeText(activity, "There were no recipes found", Toast.LENGTH_LONG).show();

            return;
        } else {
            Intent intent = new Intent(activity, RecipeActivity.class);
            intent.putExtra("RecipeList", newRecipeList);
            //activity.dialog.dismiss();
            //activity.startActivity(intent);

            activity.startActivityForResult(intent,100);
            //return recipes;
        }


    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        activity.progressBar.setProgress(count*5);
    }

    @Override
    protected List<Recipe> doInBackground(List<String>... lists) {


        List<String> recipeList = lists[0];
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
        Log.d("demoFound", ingredients.toString() + dish);


        RequestParams requestParams = new RequestParams("GET", "http://www.recipepuppy.com/api");
        requestParams.addParam("format", "xml");
        requestParams.addParam("q", dish);
        requestParams.addParam("i", ingredients.toString());


        HttpURLConnection connection = (HttpURLConnection) requestParams.setupConnection();
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {


            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while (null != (line = bufferedReader.readLine())) {
                    publishProgress(count++);
                    stringBuilder.append(line);
                }

                Log.d("demo", stringBuilder.toString());

                return RecipeUtil.PersonsPullParser.parseRecipes(stringBuilder.toString());

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


        return null;
    }
}

