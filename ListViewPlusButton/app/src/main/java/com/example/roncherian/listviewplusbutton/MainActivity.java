package com.example.roncherian.listviewplusbutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String>strings = new ArrayList<String>();
        strings.add("");
        ListView resultsListView = (ListView)findViewById(R.id.listView);
        ListViewAdapter favoritesListAdapter = new ListViewAdapter(this, R.layout.add_button_layout, strings);
        resultsListView.setAdapter(favoritesListAdapter);
        favoritesListAdapter.setNotifyOnChange(true);
    }


}
