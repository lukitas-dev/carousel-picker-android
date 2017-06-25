package com.ldealmei.libs.carousel.model;

/**
 * Created by Lucas Cruz on 27/05/2017.
 */

public class ItemPicker {

    public int imgResID;
    public String imgUrl;
    public int txtResID;
    public String txt;

    public boolean hasDescription = true;

    public ItemPicker(int imgResID) {
        this.imgResID = imgResID;
        this.hasDescription = false;
    }

    public ItemPicker(String imgUrl) {
        this.imgUrl = imgUrl;
        this.hasDescription = false;
    }

    public ItemPicker(int imgResID, int txtResID) {
        this.imgResID = imgResID;
        this.txtResID = txtResID;
    }

    public ItemPicker(int imgResID, String txt) {
        this.imgResID = imgResID;
        this.txt = txt;
    }

    public ItemPicker(String imgUrl, String txt) {
        this.imgUrl = imgUrl;
        this.txt = txt;
    }
}
