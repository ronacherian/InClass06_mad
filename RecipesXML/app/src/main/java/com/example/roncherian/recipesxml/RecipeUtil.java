package com.example.roncherian.recipesxml;
//Class Assignemnt: 6
//Ron Abraham Cherian
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Created by roncherian on 02/10/17.
 */

public class RecipeUtil {

    static public class PersonsPullParser{

        static public ArrayList<Recipe> parseRecipes(String xml ) throws XmlPullParserException, IOException {

            ArrayList<Recipe>recipesArrayList = new ArrayList<Recipe>();
            Recipe recipe = null;
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();

            byte[] bytes = xml.getBytes(StandardCharsets.UTF_8);
            //ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            InputStream inputStream = new ByteArrayInputStream(bytes);
            parser.setInput(inputStream, "UTF-8");
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT){

                switch (eventType){
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("recipe")){
                            recipe = new Recipe();
                            //person.setId(parser.getAttributeValue(null,"id"));//First one is name space

                        } else if (parser.getName().equals("title")){
                            recipe.setTitle(parser.nextText().trim());
                        } else if (parser.getName().equals("href")){
                            recipe.setUrl(parser.nextText().trim());
                        } else if (parser.getName().equals("ingredients")){
                            recipe.setIngredients(parser.nextText().trim());
                        }

                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("recipe") && recipe!=null){
                            recipesArrayList.add(recipe);
                            recipe = null;
                        }
                }

                eventType = parser.next();
            }
            return  recipesArrayList;
        }
    }
}
