package com.imperium.autobeacon;

/**
 * Created by Sowmya on 7/11/2016.
 */


 public class RowItem {

    private String restriction_name;
    private int restriction_image;

    public RowItem(String restriction_name, int restriction_image) {

        this.restriction_name = restriction_name;
        this.restriction_image = restriction_image;

    }

    public String getRestriction_name() {
        return restriction_name;
    }

    public void setRestriction_name(String restriction_name) {
        this.restriction_name = restriction_name;
    }

    public int getRestriction_image() {
        return restriction_image;
    }

    public void setRestriction_image(int restriction_image) {
        this.restriction_image = restriction_image;
    }



}