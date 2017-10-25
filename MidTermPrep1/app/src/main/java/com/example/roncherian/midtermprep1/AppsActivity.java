package com.example.roncherian.midtermprep1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AppsActivity extends AppCompatActivity {

    private static SharedPreferences sharedPreferences = null;

    private static SharedPreferences.Editor editor = null;

    ArrayList<ApiFeedModel> feeds = new ArrayList<ApiFeedModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);

        feeds= (ArrayList<ApiFeedModel>) getIntent().getSerializableExtra("FEEDS");

        ListView resultsListView = (ListView)findViewById(R.id.listView1);

        ApiFeedAdapter feedAdapter = new ApiFeedAdapter(this, R.layout.apps_activity_row, feeds);
        resultsListView.setAdapter(feedAdapter);
        feedAdapter.setNotifyOnChange(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.apps_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_sort_dev_name:
                /*Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);*/
                if (!this.getClass().equals(MainActivity.class)){
                    setResult(Activity.RESULT_OK);
                    finish();
                }

                return true;

            case R.id.action_clear_history:
                //finish();
                //android.os.Process.killProcess(android.os.Process.myPid());
                //System.exit(1);
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                return true;

            case R.id.action_sort_app_name:

                Collections.sort(feeds,ApiFeedModel.COMPARE_BY_PHONE);
                ListView resultsListView = (ListView)findViewById(R.id.listView1);

                ApiFeedAdapter feedAdapter = new ApiFeedAdapter(this, R.layout.apps_activity_row, feeds);
                resultsListView.setAdapter(feedAdapter);
                feedAdapter.setNotifyOnChange(true);

                Log.d("demo","hello");
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public <T> void setList(String key, List<ApiFeedModel> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        set(key, json);
    }

    public static void set(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public static List<ApiFeedModel> get(String key){
        String val = sharedPreferences.getString(key, null);
        if (val==null){
            return  new ArrayList<ApiFeedModel>();
        }

            /*JSONArray myJson = new JSONArray(val);
            List<MusicTrack>t = (List<MusicTrack>)myJson;*/
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ApiFeedModel>>(){}.getType();

        ArrayList<ApiFeedModel> mMusicObject = gson.fromJson(val, type);

        return mMusicObject;

    }

}
