package com.ldealmei.libs.carousel.model;

/**
 * Created by Lucas Cruz on 27/05/2017.
 */

public class ItemPicker {

    public int imgResID;
    public int txtResID;
    public String txt;

    public boolean hasDescription;

    public ItemPicker(int imgResID) {
        this.imgResID = imgResID;
        this.hasDescription = false;
    }

    public ItemPicker(int imgResID, int txtResID) {
        this.imgResID = imgResID;
        this.txtResID = txtResID;
        this.hasDescription = true;
    }

    public ItemPicker(int imgResID, String txt) {
        this.imgResID = imgResID;
        this.txt = txt;
        this.hasDescription = true;
    }

}
