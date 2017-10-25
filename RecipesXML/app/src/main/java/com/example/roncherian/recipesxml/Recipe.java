package com.example.roncherian.recipesxml;
//Class Assignemnt: 6
//Ron Abraham Cherian
import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by roncherian on 02/10/17.
 */

public class Recipe implements Serializable {

    String title, ingredients, url;
    Bitmap bitmap;

    byte[] imageByteArray;

    public byte[] getImageByteArray() {
        return imageByteArray;
    }


    public void setImageByteArray(byte[] imageByteArray) {
        this.imageByteArray = imageByteArray;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }


}
