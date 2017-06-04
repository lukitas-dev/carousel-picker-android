package com.lcruz.carouselpicker;

/**
 * Created by Lucas Cruz on 27/05/2017.
 */

public class ItemPicker {

    public int imgResID;
    public int txtResID;
    public String txt;

    public ItemPicker(int imgResID, int txtResID) {
        this.imgResID = imgResID;
        this.txtResID = txtResID;
    }

    public ItemPicker(int imgResID, String txt) {
        this.imgResID = imgResID;
        this.txt = txt;
    }
}
