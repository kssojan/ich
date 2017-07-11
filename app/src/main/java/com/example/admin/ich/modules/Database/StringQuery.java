package com.example.admin.ich.modules.Database;

/**
 * Created by admin on 11/24/2016.
 */


/**
 * Created by admin on 11/14/2016.
 */
public class StringQuery {


    public static final String cartMenuQuery = "Create table "
            + ColumnID.MENU_TABLE + "(" + ColumnID.ID + " integer primary key autoincrement,"
            +ColumnID.MENU_ID+" text," + ColumnID.MENU_NAME+" text,"+ ColumnID.MENU_PIC+" text,"
            +ColumnID.COUNT+" text,"
            +ColumnID.PRICE+" text,"
            +ColumnID.SINGLE_SELECTION+" text,"
            +ColumnID.SINGLE_SELECTION_PRICE+" text,"+ColumnID.TOTAL+" text);";

    public static final String cartMenuOptionCheckboxQuery = "Create table "
            + ColumnID.MENU_OPTION_TABLE_CHECKBOX + "(" + ColumnID.ID + " integer primary key autoincrement,"
            +ColumnID.MENU_ID+" text," + ColumnID.COUNT+" text,"+ ColumnID.MENU_OPTION_ID+" text,"
            +ColumnID.OPTION_ID+" text,"
            +ColumnID.OPTION_NAME+" text," +ColumnID.DISPLAY_TYPE+" text," +ColumnID.OPTION_VALUE_ID+" text,"
            +ColumnID.MENU_OPTION_VALUE_ID+" text,"+ColumnID.VALUE+" text,"
            +ColumnID.OPTION_PRICE+" text,"+ColumnID.OPTION_TOTAL+" text);";

    public static final String cartMenuOptionRadioQuery = "Create table "
            + ColumnID.MENU_OPTION_TABLE_RADIO + "(" + ColumnID.ID + " integer primary key autoincrement,"
            +ColumnID.MENU_ID+" text," + ColumnID.COUNT+" text,"+ ColumnID.MENU_OPTION_ID+" text,"
            +ColumnID.OPTION_ID+" text,"
            +ColumnID.OPTION_NAME+" text," +ColumnID.DISPLAY_TYPE+" text," +ColumnID.OPTION_VALUE_ID+" text,"
            +ColumnID.MENU_OPTION_VALUE_ID+" text,"+ColumnID.VALUE+" text,"
            +ColumnID.OPTION_PRICE+" text,"+ColumnID.OPTION_TOTAL+" text);";

    public static final String cartMenuOptionSelectQuery = "Create table "
            + ColumnID.MENU_OPTION_TABLE_SELECT + "(" + ColumnID.ID + " integer primary key autoincrement,"
            +ColumnID.MENU_ID+" text," + ColumnID.COUNT+" text,"+ ColumnID.MENU_OPTION_ID+" text,"
            +ColumnID.OPTION_ID+" text,"
            +ColumnID.OPTION_NAME+" text," +ColumnID.DISPLAY_TYPE+" text," +ColumnID.OPTION_VALUE_ID+" text,"
            +ColumnID.MENU_OPTION_VALUE_ID+" text,"+ColumnID.VALUE+" text,"
            +ColumnID.OPTION_PRICE+" text,"+ColumnID.OPTION_TOTAL+" text);";


}
