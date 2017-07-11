package com.example.admin.ich.modules.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.ich.modules.Home.OptionJson;
import com.example.admin.ich.retrofit.model.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 11/24/2016.
 */


/**
 * Created by admin on 11/14/2016.
 */
public class IchDao {
    DatabaseHelper dbHelper;
    SQLiteDatabase database;

    public IchDao(Context context) {
        super();
        dbHelper = new DatabaseHelper(context);

    }

    public void openDB() {

        database = dbHelper.getWritableDatabase();

    }

    public void closeDB() {

        database.close();
    }

    public String getTotal() {
        String type = "0.00";
        openDB();
        Cursor cursor = database.rawQuery("SELECT SUM(" + (ColumnID.TOTAL) + ") FROM " + ColumnID.MENU_TABLE, null);
        if (cursor != null && cursor.moveToFirst()) {

            type = cursor.getString(cursor.getColumnIndex(ColumnID.TOTAL));
        }
        closeDB();
        return type;

    }
    public void insertOptionCart(List<OptionJson> optionValueArrayList) {
        for (int i=0;i<optionValueArrayList.size();i++) {
            ContentValues values = new ContentValues();
            values.put(ColumnID.MENU_ID, optionValueArrayList.get(i).getMenu_id());
            values.put(ColumnID.COUNT, optionValueArrayList.get(i).getCount());
            values.put(ColumnID.MENU_OPTION_ID, optionValueArrayList.get(i).getMenu_option_id());
            values.put(ColumnID.OPTION_ID, optionValueArrayList.get(i).getOption_id());
            values.put(ColumnID.OPTION_NAME, optionValueArrayList.get(i).getOption_name());
            values.put(ColumnID.DISPLAY_TYPE, optionValueArrayList.get(i).getDisplay_type());

            values.put(ColumnID.OPTION_VALUE_ID, optionValueArrayList.get(i).getOption_value_id());
            values.put(ColumnID.MENU_OPTION_VALUE_ID, optionValueArrayList.get(i).getMenu_option_value_id());
            values.put(ColumnID.VALUE, optionValueArrayList.get(i).getValue());
            values.put(ColumnID.OPTION_PRICE, optionValueArrayList.get(i).getOption_price());
            values.put(ColumnID.OPTION_TOTAL, optionValueArrayList.get(i).getOption_total());

            openDB();
            database.beginTransaction();
            database.insert(ColumnID.MENU_OPTION_TABLE_CHECKBOX, null, values);
            database.setTransactionSuccessful();
            database.endTransaction();
            closeDB();
        }
    }

    public void insetCart(String menuid, String menuname, String pic, String count,
                          String price, String singleselection, String singleprice, String total) {
        ContentValues values = new ContentValues();
        values.put(ColumnID.MENU_NAME, menuname);
        values.put(ColumnID.MENU_ID, menuid);
        values.put(ColumnID.MENU_PIC, pic);
        values.put(ColumnID.COUNT, count);
        values.put(ColumnID.PRICE, price);

        values.put(ColumnID.SINGLE_SELECTION, singleselection);
        values.put(ColumnID.SINGLE_SELECTION_PRICE, singleprice);
        values.put(ColumnID.TOTAL, total);
        openDB();
        database.beginTransaction();
        database.insert(ColumnID.MENU_TABLE, null, values);
        database.setTransactionSuccessful();
        database.endTransaction();
        closeDB();
    }

    public void updateCart(String menuid, String count, String total) {
        ContentValues values = new ContentValues();
        values.put(ColumnID.COUNT, count);
        values.put(ColumnID.TOTAL, total);


        openDB();
        database.beginTransaction();

        database.update(ColumnID.MENU_TABLE, values, "menu_id=" + menuid, null);
        database.setTransactionSuccessful();
        database.endTransaction();
        closeDB();
    }

    public void deleteOptionCart(String menuid) {



        openDB();
        database.beginTransaction();

        database.delete(ColumnID.MENU_OPTION_TABLE_CHECKBOX,  "menu_id=" + menuid, null);
        database.setTransactionSuccessful();
        database.endTransaction();
        closeDB();
    }



    public void deleteMenu(String menuid) {
        openDB();
        database.beginTransaction();

        database.delete(ColumnID.MENU_TABLE,  "menu_id=" + menuid, null);
        database.setTransactionSuccessful();
        database.endTransaction();
        closeDB();
    }

    public ArrayList<Menu> getTotalPrice() {
        ArrayList<Menu> countyList = new ArrayList<Menu>();
        openDB();
        String query = "Select * from " + ColumnID.MENU_TABLE;
        Cursor cursor = database.rawQuery(query, null);


        while (cursor.moveToNext()) {
            Menu county = new Menu();


            county.setTotalcount(cursor.getString(cursor
                    .getColumnIndex(ColumnID.TOTAL)));


            countyList.add(county);

        }
        return countyList;

    }

    public ArrayList<OptionJson> getMenuOptionItemOption(String id) {
        ArrayList<OptionJson> countyList = new ArrayList<OptionJson>();
        openDB();
        String query = "Select * from " + ColumnID.MENU_OPTION_TABLE_CHECKBOX + " where " + ColumnID.MENU_ID + " = " + id;
        Cursor cursor = database.rawQuery(query, null);


        while (cursor.moveToNext()) {
            OptionJson county = new OptionJson();


            county.setOption_total(cursor.getString(cursor
                    .getColumnIndex(ColumnID.OPTION_TOTAL)));
            county.setValue(cursor.getString(cursor
                    .getColumnIndex(ColumnID.VALUE)));
            county.setOption_price(cursor.getString(cursor
                    .getColumnIndex(ColumnID.OPTION_PRICE)));


            countyList.add(county);

        }
        return countyList;

    }

    public ArrayList<OptionJson> getTotalPriceOption() {
        ArrayList<OptionJson> countyList = new ArrayList<OptionJson>();
        openDB();
        String query = "Select * from " + ColumnID.MENU_OPTION_TABLE_CHECKBOX;
        Cursor cursor = database.rawQuery(query, null);


        while (cursor.moveToNext()) {
            OptionJson county = new OptionJson();


            county.setOption_total(cursor.getString(cursor
                    .getColumnIndex(ColumnID.OPTION_TOTAL)));
            county.setValue(cursor.getString(cursor
                    .getColumnIndex(ColumnID.VALUE)));
            county.setOption_price(cursor.getString(cursor
                    .getColumnIndex(ColumnID.OPTION_PRICE)));


            countyList.add(county);

        }
        return countyList;

    }

    public ArrayList<Menu> getCart() {
        ArrayList<Menu> countyList = new ArrayList<Menu>();
        openDB();
        String query = "Select distinct " + ColumnID.MENU_ID + " from " + ColumnID.MENU_TABLE;
        Cursor cursor = database.rawQuery(query, null);


        while (cursor.moveToNext()) {
            Menu county = new Menu();

            county.setMenuId(cursor.getString(cursor
                    .getColumnIndex(ColumnID.MENU_ID)));

           /* county.setTotalcount(cursor.getString(cursor
                    .getColumnIndex(ColumnID.TOTAL)));*/


            countyList.add(county);

        }
        return countyList;

    }

    public ArrayList<OptionJson> getCartRadio(String menuid) {
        ArrayList<OptionJson> countyList = new ArrayList<OptionJson>();
        openDB();
        String query = "Select distinct " + ColumnID.MENU_OPTION_VALUE_ID + " from " + ColumnID.MENU_OPTION_TABLE_CHECKBOX
                + " where " + ColumnID.MENU_ID + " = " + menuid +" and "+ ColumnID.DISPLAY_TYPE + " = " + "'radio'" ;
        Cursor cursor = database.rawQuery(query, null);


        while (cursor.moveToNext()) {
            OptionJson county = new OptionJson();

            county.setMenu_option_value_id(cursor.getString(cursor
                    .getColumnIndex(ColumnID.MENU_OPTION_VALUE_ID)));


            countyList.add(county);

        }
        return countyList;

    }

    public ArrayList<OptionJson> getCartCheckbox(String menuid) {
        ArrayList<OptionJson> countyList = new ArrayList<OptionJson>();
        openDB();
        String query = "Select distinct " + ColumnID.MENU_OPTION_VALUE_ID + " from " + ColumnID.MENU_OPTION_TABLE_CHECKBOX
                + " where " + ColumnID.MENU_ID + " = " + menuid +" and "+ ColumnID.DISPLAY_TYPE + " = " + "'checkbox'" ;
        Cursor cursor = database.rawQuery(query, null);


        while (cursor.moveToNext()) {
            OptionJson county = new OptionJson();

            county.setMenu_option_value_id(cursor.getString(cursor
                    .getColumnIndex(ColumnID.MENU_OPTION_VALUE_ID)));


            countyList.add(county);

        }
        return countyList;

    }

    public ArrayList<OptionJson> getCartSelect(String menuid) {
        ArrayList<OptionJson> countyList = new ArrayList<OptionJson>();
        openDB();
        String query = "Select distinct * from " + ColumnID.MENU_OPTION_TABLE_CHECKBOX
                + " where " + ColumnID.MENU_ID + " = " + menuid +" and "+ ColumnID.DISPLAY_TYPE + " = " + "'select'" ;
        Cursor cursor = database.rawQuery(query, null);


        while (cursor.moveToNext()) {
            OptionJson county = new OptionJson();

            county.setMenu_option_value_id(cursor.getString(cursor
                    .getColumnIndex(ColumnID.MENU_OPTION_VALUE_ID)));
            county.setValue(cursor.getString(cursor
                    .getColumnIndex(ColumnID.VALUE)));
            county.setOption_price(cursor.getString(cursor
                    .getColumnIndex(ColumnID.OPTION_PRICE)));


            countyList.add(county);

        }
        return countyList;

    }


    public ArrayList<Menu> getCartMenus() {
        ArrayList<Menu> countyList = new ArrayList<Menu>();
        openDB();
        String query = "Select * from " + ColumnID.MENU_TABLE;
        Cursor cursor = database.rawQuery(query, null);


        while (cursor.moveToNext()) {
            Menu county = new Menu();

            county.setMenuId(cursor.getString(cursor
                    .getColumnIndex(ColumnID.MENU_ID)));

            county.setImagePath(cursor.getString(cursor
                    .getColumnIndex(ColumnID.MENU_PIC)));

            county.setCount(cursor.getString(cursor
                    .getColumnIndex(ColumnID.COUNT)));

            county.setMenuPrice(cursor.getString(cursor
                    .getColumnIndex(ColumnID.PRICE)));

            county.setMenuName(cursor.getString(cursor
                    .getColumnIndex(ColumnID.MENU_NAME)));
            county.setMenuOptionSelection(cursor.getString(cursor
                    .getColumnIndex(ColumnID.SINGLE_SELECTION)));


            countyList.add(county);

        }
        return countyList;

    }


    public ArrayList<Menu> getCartItem(String id) {
        ArrayList<Menu> countyList = new ArrayList<Menu>();
        openDB();
        String query = "Select *  from " + ColumnID.MENU_TABLE + " where " + ColumnID.MENU_ID + " = " + id;
        Cursor cursor = database.rawQuery(query, null);


        while (cursor.moveToNext()) {
            Menu county = new Menu();

            county.setMenuId(cursor.getString(cursor
                    .getColumnIndex(ColumnID.MENU_ID)));

            county.setTotalcount(cursor.getString(cursor
                    .getColumnIndex(ColumnID.TOTAL)));
            county.setCount(cursor.getString(cursor
                    .getColumnIndex(ColumnID.COUNT)));


            countyList.add(county);

        }
        return countyList;

    }


    public ArrayList<Menu> getAllCartItem() {
        ArrayList<Menu> countyList = new ArrayList<Menu>();
        openDB();
        String query = "SELECT * FROM " + ColumnID.MENU_TABLE;
                /*+ " LEFT JOIN " + ColumnID.MENU_OPTION_TABLE_CHECKBOX + " ON "
                + ColumnID.MENU_TABLE + "." + ColumnID.MENU_ID + " = "
                + ColumnID.MENU_OPTION_TABLE_CHECKBOX + "." + ColumnID.MENU_ID ;*/
        Cursor cursor = database.rawQuery(query, null);


        while (cursor.moveToNext()) {
            Menu county = new Menu();

            county.setMenuId(cursor.getString(cursor
                    .getColumnIndex(ColumnID.MENU_ID)));
            county.setTotalcount(cursor.getString(cursor
                    .getColumnIndex(ColumnID.TOTAL)));
            county.setCount(cursor.getString(cursor
                    .getColumnIndex(ColumnID.COUNT)));
            county.setMenuName(cursor.getString(cursor
                    .getColumnIndex(ColumnID.MENU_NAME)));
            county.setMenuPrice(cursor.getString(cursor
                    .getColumnIndex(ColumnID.PRICE)));

           /* county.setValueId(cursor.getString(cursor
                    .getColumnIndex(ColumnID.MENU_OPTION_VALUE_ID)));
            county.setValueName(cursor.getString(cursor
                    .getColumnIndex(ColumnID.VALUE)));
            county.setValuePrice(cursor.getString(cursor
                    .getColumnIndex(ColumnID.OPTION_PRICE)));*/




            countyList.add(county);

        }
        return countyList;

    }

    public ArrayList<Menu> getAllCartOptionItem(String id) {
        ArrayList<Menu> countyList = new ArrayList<Menu>();
        openDB();
        String query = "SELECT * FROM " + ColumnID.MENU_OPTION_TABLE_CHECKBOX
                + " where " + ColumnID.MENU_ID + " = " + id;

        Cursor cursor = database.rawQuery(query, null);


        while (cursor.moveToNext()) {
            Menu county = new Menu();

            county.setMenuId(cursor.getString(cursor
                    .getColumnIndex(ColumnID.MENU_ID)));
            county.setCount(cursor.getString(cursor
                    .getColumnIndex(ColumnID.COUNT)));
            county.setValueId(cursor.getString(cursor
                    .getColumnIndex(ColumnID.MENU_OPTION_VALUE_ID)));
            county.setValueName(cursor.getString(cursor
                    .getColumnIndex(ColumnID.VALUE)));
            county.setValuePrice(cursor.getString(cursor
                    .getColumnIndex(ColumnID.OPTION_PRICE)));




            countyList.add(county);

        }
        return countyList;

    }


}
