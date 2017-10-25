package com.example.roncherian.midtermprep1;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by roncherian on 15/10/17.
 */

public class ApiFeedModel implements Serializable{

    String appId, appTitle, appUrl, developerName, appPrice, appSmallUrl, appLargeUrl, releaseDate;

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppTitle() {
        return appTitle;
    }

    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getAppPrice() {
        return appPrice;
    }

    public void setAppPrice(String appPrice) {
        this.appPrice = appPrice;
    }

    public String getAppSmallUrl() {
        return appSmallUrl;
    }

    public void setAppSmallUrl(String appSmallUrl) {
        this.appSmallUrl = appSmallUrl;
    }

    public String getAppLargeUrl() {
        return appLargeUrl;
    }

    public void setAppLargeUrl(String appLargeUrl) {
        this.appLargeUrl = appLargeUrl;
    }


    public static Comparator<ApiFeedModel> COMPARE_BY_PHONE = new Comparator<ApiFeedModel>() {
        public int compare(ApiFeedModel one, ApiFeedModel other) {
            return one.getAppTitle().compareTo(other.getAppTitle());
        }
    };

    /*public static Comparator<Contact> COMPARE_BY_ADDRESS = new Comparator<Contact>() {
        public int compare(Contact one, Contact other) {
            return one.address.compareTo(other.address);
        }
    };*/
}
