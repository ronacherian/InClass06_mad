package com.example.roncherian.simplelistview;

/**
 * Created by roncherian on 07/10/17.
 */

public class Color {

    String colorName, colorHex;

    public Color(String colorName, String colorHex) {
        this.colorName = colorName;
        this.colorHex = colorHex;
    }

    @Override
    public String toString() {
        return this.colorName+this.colorHex;
    }
}
