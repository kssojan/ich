
package com.example.admin.ich.retrofit.model.MenuOption;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuOptions {

    @SerializedName("radio")
    @Expose
    private Radio radio;
    @SerializedName("checkbox")
    @Expose
    private Checkbox checkbox;
    @SerializedName("select")
    @Expose
    private Select select;

    public Radio getRadio() {
        return radio;
    }

    public void setRadio(Radio radio) {
        this.radio = radio;
    }

    public Checkbox getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(Checkbox checkbox) {
        this.checkbox = checkbox;
    }

    public Select getSelect() {
        return select;
    }

    public void setSelect(Select select) {
        this.select = select;
    }

}
