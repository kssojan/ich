package com.example.admin.ich.retrofit.model.MenuOption;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 7/11/2017.
 */

public class Options {

    @SerializedName("value_id")
    @Expose
    private String value_id;
    @SerializedName("value_name")
    @Expose
    private String value_name;
    @SerializedName("value_price")
    private  String value_price;

    public String getValue_id() {
        return value_id;
    }

    public void setValue_id(String value_id) {
        this.value_id = value_id;
    }

    public String getValue_name() {
        return value_name;
    }

    public void setValue_name(String value_name) {
        this.value_name = value_name;
    }

    public String getValue_price() {
        return value_price;
    }

    public void setValue_price(String value_price) {
        this.value_price = value_price;
    }
}
