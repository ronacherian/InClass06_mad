package com.example.roncherian.simplelistview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by roncherian on 07/10/17.
 */

public class ColorAdapter extends ArrayAdapter<Color> {


    Context mContext;
    int mResource;
    ArrayList<Color>mObjects;

    public ColorAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Color> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.mObjects = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(mResource,parent,false);
        }

        TextView colorNameTextView = (TextView)convertView.findViewById(R.id.textViewColorName);
        colorNameTextView.setText(mObjects.get(position).colorName);

        TextView colorHexTextView = (TextView)convertView.findViewById(R.id.textViewColorHex);
        colorHexTextView.setText(mObjects.get(position).colorHex);
        colorHexTextView.setTextColor(android.graphics.Color.parseColor(mObjects.get(position).colorHex));
        return convertView;
    }
}
