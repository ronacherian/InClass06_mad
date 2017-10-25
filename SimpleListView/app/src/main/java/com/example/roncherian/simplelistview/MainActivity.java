package com.example.roncherian.simplelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<Color>colors = new ArrayList<Color>();
        colors.add(new Color("Red","#FF0000"));
        colors.add(new Color("Green","#006600"));
        colors.add(new Color("Blue","#0000FF"));
        colors.add(new Color("Black","#000000"));
        colors.add(new Color("Gray","#BBBBBB"));

        ListView listView = (ListView)findViewById(R.id.listView1);
        //ArrayAdapter<Color> adapter = new ArrayAdapter<Color>(this,android.R.layout.simple_list_item_1,colors);
        ColorAdapter adapter = new ColorAdapter(this,R.layout.layout_color_row,colors);

        listView.setAdapter(adapter);
        adapter.setNotifyOnChange(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("demo","Clicked:"+i+" color:"+colors.get(i));

            }
        });

//        adapter.add(new Color("Purple"));
//        adapter.remove(colors.get(0));
//        adapter.insert(new Color("Brown"),0);
        //adapter.notifyDataSetChanged();
    }
}
