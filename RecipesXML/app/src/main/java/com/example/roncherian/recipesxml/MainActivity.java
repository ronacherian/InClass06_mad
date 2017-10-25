package com.example.roncherian.recipesxml;
//Class Assignemnt: 6
//Ron Abraham Cherian
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static enum  BUTTON_TYPE  { ADD, REMOVE};
    int count = 0;
    List<String> ingredientsListToSearch = new ArrayList<String>();
    ProgressBar progressBar;
    //ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Recipe Puppy");
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final LinearLayout parentView = (LinearLayout) findViewById(R.id.editPage);
        final LinearLayout linearLayout1 = (LinearLayout)findViewById(R.id.linearLayout);
        final TextView textView = (TextView)findViewById(R.id.textViewIngred);


        final Button searchButton = (Button)findViewById(R.id.buttonSearch);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextDish = (EditText)findViewById(R.id.editTextDish);
                if (editTextDish.getText().toString().length() == 0){
                    Toast.makeText(MainActivity.this,"Please Enter valid dish",Toast.LENGTH_LONG).show();
                    return;
                }else {
                    parentView.setVisibility(View.INVISIBLE);
                    linearLayout1.setVisibility(View.INVISIBLE);
                    textView.setVisibility(View.INVISIBLE);
                    searchButton.setVisibility(View.INVISIBLE);
                    findViewById(R.id.textViewLoading).setVisibility(View.VISIBLE);

                    ingredientsListToSearch = new ArrayList<String>();
                    ingredientsListToSearch.add(editTextDish.getText().toString().trim());
                    String dishName = editTextDish.getText().toString().trim();
                    for (int i = 0; i<=count; i++){
                        LinearLayout layout = (LinearLayout) parentView.getChildAt(i);
                        EditText editText = (EditText)layout.findViewById(R.id.editTextIngredientSearch);
                        String text = editText.getText().toString().trim();

                        if (text.length() > 0){
                            Log.d("demo",text);
                            ingredientsListToSearch.add(text);
                        }

                    }

                    new GetDataAsyncTask(MainActivity.this).execute(ingredientsListToSearch);
                }


            }
        });


        LayoutInflater inflator1 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        LinearLayout linearLayout = (LinearLayout) inflator1.inflate(R.layout.search_layout, null);
        linearLayout.setTag(0);

        Button button = (Button) linearLayout.findViewById(R.id.buttonAdd);
        button.setTag(BUTTON_TYPE.ADD);
        parentView.addView(linearLayout);


        addOrRemoveView(parentView,button);
    }


    private void  addOrRemoveView(final LinearLayout parentView, Button button){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button parentButton = (Button)view;


                if (parentButton.getTag() == BUTTON_TYPE.ADD){

                    parentButton.setTag(BUTTON_TYPE.REMOVE);
                    parentButton.setBackgroundResource(R.drawable.remove);
                    LinearLayout parentView = (LinearLayout) findViewById(R.id.editPage);
                    LayoutInflater inflator1 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    LinearLayout linearLayout = (LinearLayout) inflator1.inflate(R.layout.search_layout, null);
                    linearLayout.setTag(++count);
                    EditText searchText = (EditText)linearLayout.findViewById(R.id.editTextIngredientSearch);
                    Button addOrRemoveButton = (Button)linearLayout.findViewById(R.id.buttonAdd);
                    if (count >= 4){
                        addOrRemoveButton.setTag(BUTTON_TYPE.REMOVE);
                        addOrRemoveButton.setBackgroundResource(R.drawable.remove);
                    } else {
                        addOrRemoveButton.setTag(BUTTON_TYPE.ADD);
                    }

                    parentView.addView(linearLayout);
                    addOrRemoveView(parentView,addOrRemoveButton);

                } else if (parentButton.getTag() == BUTTON_TYPE.REMOVE){
                    parentButton.setTag(BUTTON_TYPE.ADD);
                    parentButton.setBackgroundResource(R.drawable.add);
                    LinearLayout layout = (LinearLayout) parentButton.getParent();
                    parentView.removeView(layout);
                    if (count == 4){
                        LinearLayout layout2 = (LinearLayout) parentView.getChildAt(count-1);
                        Button newButton = layout2.findViewById(R.id.buttonAdd);
                        newButton.setTag(BUTTON_TYPE.ADD);
                        newButton.setBackgroundResource(R.drawable.add);
                    }

                    count--;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100){
            Log.d("demoFound","hello");

            LinearLayout parentView = (LinearLayout) findViewById(R.id.editPage);
            LinearLayout linearLayout1 = (LinearLayout)findViewById(R.id.linearLayout);
            TextView textView = (TextView)findViewById(R.id.textViewIngred);
            Button searchButton = (Button)findViewById(R.id.buttonSearch);

            parentView.setVisibility(View.VISIBLE);
            linearLayout1.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            searchButton.setVisibility(View.VISIBLE);
            findViewById(R.id.textViewLoading).setVisibility(View.INVISIBLE);
        }
    }
}
