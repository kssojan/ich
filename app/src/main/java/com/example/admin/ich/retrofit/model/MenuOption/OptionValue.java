
package com.example.admin.ich.retrofit.model.MenuOption;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OptionValue {

    @SerializedName("option_value_id")
    @Expose
    private String optionValueId;
    @SerializedName("menu_option_value_id")
    @Expose
    private String menuOptionValueId;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("price")
    @Expose
    private String price;

    private boolean isSelected;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getOptionValueId() {
        return optionValueId;
    }

    public void setOptionValueId(String optionValueId) {
        this.optionValueId = optionValueId;
    }

    public String getMenuOptionValueId() {
        return menuOptionValueId;
    }

    public void setMenuOptionValueId(String menuOptionValueId) {
        this.menuOptionValueId = menuOptionValueId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
