package com.example.roncherian.midtermprep1;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Created by roncherian on 15/10/17.
 */

public class AppsActivityUtil {
    static public class PersonsPullParser{

        static public ArrayList<ApiFeedModel> parseRecipes(String xml ) throws XmlPullParserException, IOException {

            ArrayList<ApiFeedModel>apiFeedsArrayList = new ArrayList<ApiFeedModel>();
            ApiFeedModel appFeed = null;
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();

            byte[] bytes = xml.getBytes(StandardCharsets.UTF_8);
            //ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            InputStream inputStream = new ByteArrayInputStream(bytes);
            parser.setInput(inputStream, "UTF-8");
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT){

                switch (eventType){
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("entry")){
                            appFeed = new ApiFeedModel();
                            //person.setId(parser.getAttributeValue(null,"id"));//First one is name space

                        } else if(appFeed!=null) {
                            if (parser.getName().equals("id")) {

                                if (parser.getAttributeValue(null, "im:id") != null)
                                    appFeed.setAppId(parser.getAttributeValue(null, "im:id"));
                            } else if (parser.getName().equals("title")){
                                    appFeed.setAppTitle(parser.nextText().trim());
                            } else if (parser.getName().equals("link")){
                                    appFeed.setAppUrl(parser.getAttributeValue(null,"href"));
                            } else if (parser.getName().equals("im:artist")){
                                    appFeed.setDeveloperName(parser.getAttributeValue(null,"href"));
                            } else if (parser.getName().equals("im:price")){
                                appFeed.setAppPrice(parser.getAttributeValue(null,"amount"));
                            } else if (parser.getName().equals("im:image")){
                                appFeed.setAppLargeUrl(parser.nextText().trim());
                            } else if (parser.getName().equals("im:releaseDate")){
                                appFeed.setReleaseDate(parser.nextText().trim());
                            }
                        }


                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("entry") && appFeed!=null){
                            apiFeedsArrayList.add(appFeed);
                            appFeed = null;
                        }
                }

                eventType = parser.next();
            }
            return  apiFeedsArrayList;
        }
    }
}
