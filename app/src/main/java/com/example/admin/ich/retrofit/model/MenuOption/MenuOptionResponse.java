
package com.example.admin.ich.retrofit.model.MenuOption;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuOptionResponse {

    @SerializedName("menu_options")
    @Expose
    private MenuOptions menuOptions;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;

    public MenuOptions getMenuOptions() {
        return menuOptions;
    }

    public void setMenuOptions(MenuOptions menuOptions) {
        this.menuOptions = menuOptions;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
