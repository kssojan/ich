
package com.example.admin.ich.retrofit.model.MenuOption;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Select {

    @SerializedName("required")
    @Expose
    private String required;
    @SerializedName("menu_option_id")
    @Expose
    private String menuOptionId;
    @SerializedName("menu_id")
    @Expose
    private String menuId;
    @SerializedName("option_id")
    @Expose
    private String optionId;
    @SerializedName("option_name")
    @Expose
    private String optionName;
    @SerializedName("display_type")
    @Expose
    private String displayType;
    @SerializedName("priority")
    @Expose
    private String priority;
    @SerializedName("default_value_id")
    @Expose
    private String defaultValueId;
    @SerializedName("option_values")
    @Expose
    private List<OptionValue> optionValues = null;

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getMenuOptionId() {
        return menuOptionId;
    }

    public void setMenuOptionId(String menuOptionId) {
        this.menuOptionId = menuOptionId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDefaultValueId() {
        return defaultValueId;
    }

    public void setDefaultValueId(String defaultValueId) {
        this.defaultValueId = defaultValueId;
    }

    public List<OptionValue> getOptionValues() {
        return optionValues;
    }

    public void setOptionValues(List<OptionValue> optionValues) {
        this.optionValues = optionValues;
    }

}
