
package com.example.admin.ich.retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {


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
    HashMap<String, List<Map<String, String>>> dataToPass = new HashMap<String, List<Map<String, String>>>();

    public HashMap<String, List<Map<String, String>>> getDataToPass() {
        return dataToPass;
    }

    public void setDataToPass(HashMap<String, List<Map<String, String>>> dataToPass) {
        this.dataToPass = dataToPass;
    }
// List<Map<String, String>> dataToPass = new ArrayList<Map<String, String>>();

/*
    public List<Map<String, String>> getDataToPass() {
        return dataToPass;
    }

    public void setDataToPass(List<Map<String, String>> dataToPass) {
        this.dataToPass = dataToPass;
    }
*/


    @SerializedName("menu_options")
    @Expose
    private List<String> menuOptions = null;

    public List<String> getMenuOptions() {
        return menuOptions;
    }

    public void setMenuOptions(List<String> menuOptions) {
        this.menuOptions = menuOptions;
    }


   /* public String getMenuOptions() {
        return menuOptions;
    }

    public void setMenuOptions(String menuOptions) {
        this.menuOptions = menuOptions;
    }*/

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

    public String getMenuPriority() {
        return menuPriority;
    }

    public void setMenuPriority(String menuPriority) {
        this.menuPriority = menuPriority;
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

    private String totalcount;
    @SerializedName("qty")
    @Expose
    private String count;
    private String menuOptionSelection;


    public String getMenuOptionSelection() {
        return menuOptionSelection;
    }

    public void setMenuOptionSelection(String menuOptionSelection) {
        this.menuOptionSelection = menuOptionSelection;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(String totalcount) {
        this.totalcount = totalcount;
    }


    public String getValueId() {
        return valueId;
    }

    public void setValueId(String valueId) {
        this.valueId = valueId;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public String getValuePrice() {
        return valuePrice;
    }

    public void setValuePrice(String valuePrice) {
        this.valuePrice = valuePrice;
    }

    private String valueId;
    private String valueName;
    private String valuePrice;
    private String menuOptionId;
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMenuOptionId() {
        return menuOptionId;
    }

    public void setMenuOptionId(String menuOptionId) {
        this.menuOptionId = menuOptionId;
    }

    @SerializedName("subtotal")
    @Expose
    private String valuePriceTotal;

    public String getValuePriceTotal() {
        return valuePriceTotal;
    }

    public void setValuePriceTotal(String valuePriceTotal) {
        this.valuePriceTotal = valuePriceTotal;
    }
}
