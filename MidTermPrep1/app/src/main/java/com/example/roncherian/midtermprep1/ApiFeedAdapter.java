package com.example.roncherian.midtermprep1;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roncherian on 15/10/17.
 */

public class ApiFeedAdapter  extends ArrayAdapter<ApiFeedModel> {

        private static SharedPreferences sharedPreferences = null;

        private static SharedPreferences.Editor editor = null;

        Context mContext;
        int mResource;
        ArrayList<ApiFeedModel> mObjects;

    public ApiFeedAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<ApiFeedModel> objects) {
        super(context, resource, objects);
        this.mContext = context;//context.getClass().getSimpleName().equals("SearchResultsActivity")
        this.mResource = resource;
        this.mObjects = objects;
        sharedPreferences = context.getSharedPreferences("FAVORITE", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(mResource,parent,false);
        }


        final ApiFeedModel feedModel = (ApiFeedModel)mObjects.get(position);
        TextView titleName = (TextView)convertView.findViewById(R.id.textViewAppTitle);
        titleName.setText(feedModel.getAppTitle());
        TextView releaseDate = (TextView)convertView.findViewById(R.id.textViewReleaseDate);
        releaseDate.setText(feedModel.getReleaseDate());
        TextView price = (TextView)convertView.findViewById(R.id.textViewPrice);
        price.setText(feedModel.getAppPrice());
        TextView artistNameTextView = (TextView)convertView.findViewById(R.id.textViewDeveloperName);
        artistNameTextView.setText(feedModel.getDeveloperName());


        ImageView musicTrackImageView = (ImageView)convertView.findViewById(R.id.imageViewInList);
        if (null!=feedModel && null!=feedModel.getAppLargeUrl() &&feedModel.getAppLargeUrl().length()>0){
            Picasso.with(this.mContext).load(feedModel.getAppLargeUrl()).into(musicTrackImageView);
        }
/*

        final ImageButton favoriteImageButton = (ImageButton)convertView.findViewById(R.id.imageButtonFavorite);

        List<ApiFeedModel> favoriteMusicTracks = get("FAVORITE");
        if (favoriteMusicTracks.contains(feedModel)){
            feedModel.setFavorite(true);
            favoriteImageButton.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            favoriteImageButton.setImageResource(android.R.drawable.btn_star_big_off);
            feedModel.setFavorite(false);
        }
        mObjects.set(position,feedModel);


        favoriteImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("demo","Image Button Click recognized");
                boolean isFavorite = feedModel.getFavorite();
                feedModel.setFavorite(!isFavorite);
                if (isFavorite){
                    favoriteImageButton.setImageResource(android.R.drawable.btn_star_big_off);
                    List<ApiFeedModel>musics = get("FAVORITE");
                    musics.remove(feedModel);
                    setList("FAVORITE",musics);
                    Toast.makeText(mContext,"You have removed the track: '"+feedModel.getName()+"' from favorites list",Toast.LENGTH_SHORT).show();
                } else {
                    favoriteImageButton.setImageResource(android.R.drawable.btn_star_big_on);
                    List<ApiFeedModel>musics = get("FAVORITE");
                    if (!musics.contains(feedModel)){
                        musics.add(feedModel);
                        setList("FAVORITE",musics);
                    }
                    Toast.makeText(mContext,"You have favorited the track: "+feedModel.getName(),Toast.LENGTH_SHORT).show();
                }


            }
        });*/
        return convertView;
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
