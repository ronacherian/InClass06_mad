package com.example.roncherian.listviewplusbutton;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roncherian on 16/10/17.
 */

public class ListViewAdapter  extends ArrayAdapter<String> {

    static enum  BUTTON_TYPE  { ADD, REMOVE};

    private static SharedPreferences sharedPreferences = null;

    private static SharedPreferences.Editor editor = null;

    Context mContext;
    int mResource;
    ArrayList<String> mObjects;

    public ListViewAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<String> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.mObjects = objects;
        //sharedPreferences = context.getSharedPreferences("FAVORITE", Context.MODE_PRIVATE);
        //editor = sharedPreferences.edit();
    }

    private int editingPosition =0;
    private TextWatcher watcher = (new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mObjects.set(editingPosition,s.toString());
        }
    });
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        editingPosition = position;

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(mResource,parent,false);
            //EditText ed = (EditText) convertView.findViewById(R.id.editText);

            //ed.setText(mObjects.get(position));
        }

        final EditText editText = (EditText) convertView.findViewById(R.id.editText);
        editText.removeTextChangedListener(watcher);
        final String musicTrack = (String)mObjects.get(position);



        Button button = (Button)convertView.findViewById(R.id.buttonAdd);

        if (mObjects.size()>1 && position< mObjects.size()-1){
            button.setText("Remove");
            button.setTag(BUTTON_TYPE.REMOVE);
        } else {
            button.setText("Add");
            button.setTag(BUTTON_TYPE.ADD);
        }
        editText.setText(musicTrack);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getTag() == BUTTON_TYPE.ADD){
                    mObjects.add("");
                } else if (view.getTag() == BUTTON_TYPE.REMOVE){
                    mObjects.remove(position);
                }
                editText.setText(musicTrack);
                notifyDataSetChanged();
            }
        });

       editText.addTextChangedListener(watcher);
        return convertView;
    }

    /*public <T> void setList(String key, List<MusicTrack> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        set(key, json);
    }

    public static void set(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public static List<MusicTrack> get(String key){
        String val = sharedPreferences.getString(key, null);
        if (val==null){
            return  new ArrayList<MusicTrack>();
        }

            /*JSONArray myJson = new JSONArray(val);
            List<MusicTrack>t = (List<MusicTrack>)myJson;
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<MusicTrack>>(){}.getType();

        ArrayList<MusicTrack> mMusicObject = gson.fromJson(val, type);

        return mMusicObject;

    }*/



}