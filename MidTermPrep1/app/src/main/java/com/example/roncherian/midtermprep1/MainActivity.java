package com.example.roncherian.midtermprep1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends MenuBarActivity implements GetDataAsyncTask.IApiFeeds {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button appListButton = (Button)findViewById(R.id.buttonAppList);

        Button historyButton = (Button)findViewById(R.id.buttonHistory);

        appListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetDataAsyncTask(MainActivity.this).execute();
            }
        });


        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void showResults(ArrayList<ApiFeedModel> results) {

        Log.d("demo",results.size()+"");

        Intent intent = new Intent(this,AppsActivity.class);
        intent.putExtra("FEEDS",results);
        startActivityForResult(intent,100);
    }

    @Override
    public void startProgressbar() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode==100){
            if (resultCode==RESULT_OK){

            }
        }
    }
}
