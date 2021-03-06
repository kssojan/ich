package com.example.admin.ich.retrofit.model.MenuOption;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 7/11/2017.
 */

public class MenuSaample {

    @SerializedName("menu_id")
    @Expose
    private String menuId;
    @SerializedName("menu_name")
    @Expose
    private String menuName;
    @SerializedName("menu_description")
    @Expose
    private String menuDescription;
    @SerializedName("menu_price")
    @Expose
    private String menuPrice;
    @SerializedName("menu_photo")
    @Expose
    private String menuPhoto;
    @SerializedName("menu_category_id")
    @Expose
    private String menuCategoryId;
    @SerializedName("menu_type")
    @Expose
    private String menuType;
    @SerializedName("stock_qty")
    @Expose
    private String stockQty;
    @SerializedName("minimum_qty")
    @Expose
    private String minimumQty;
    @SerializedName("subtract_stock")
    @Expose
    private String subtractStock;
    @SerializedName("mealtime_id")
    @Expose
    private String mealtimeId;
    @SerializedName("menu_status")
    @Expose
    private String menuStatus;

    public String getMenuPriority() {
        return menuPriority;
    }

    public void setMenuPriority(String menuPriority) {
        this.menuPriority = menuPriority;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getMenuPhoto() {
        return menuPhoto;
    }

    public void setMenuPhoto(String menuPhoto) {
        this.menuPhoto = menuPhoto;
    }

    public String getMenuCategoryId() {
        return menuCategoryId;
    }

    public void setMenuCategoryId(String menuCategoryId) {
        this.menuCategoryId = menuCategoryId;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getStockQty() {
        return stockQty;
    }

    public void setStockQty(String stockQty) {
        this.stockQty = stockQty;
    }

    public String getMinimumQty() {
        return minimumQty;
    }

    public void setMinimumQty(String minimumQty) {
        this.minimumQty = minimumQty;
    }

    public String getSubtractStock() {
        return subtractStock;
    }

    public void setSubtractStock(String subtractStock) {
        this.subtractStock = subtractStock;
    }

    public String getMealtimeId() {
        return mealtimeId;
    }

    public void setMealtimeId(String mealtimeId) {
        this.mealtimeId = mealtimeId;
    }

    public String getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(String menuStatus) {
        this.menuStatus = menuStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<Options> getMenuOptions() {
        return menuOptions;
    }

    public void setMenuOptions(List<Options> menuOptions) {
        this.menuOptions = menuOptions;
    }

    @SerializedName("menu_priority")
    @Expose

    private String menuPriority;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image_path")
    @Expose
    private String imagePath;
    @SerializedName("options")
    @Expose
    private List<Options> menuOptions = null;
}
