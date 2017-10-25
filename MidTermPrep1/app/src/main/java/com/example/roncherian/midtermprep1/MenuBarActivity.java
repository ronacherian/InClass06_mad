package com.example.roncherian.midtermprep1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roncherian on 15/10/17.
 */

public class MenuBarActivity extends AppCompatActivity {

    private static SharedPreferences sharedPreferences = null;

    private static SharedPreferences.Editor editor = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
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

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
