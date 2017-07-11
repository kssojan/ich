package com.example.admin.ich.modules.Database;

/**
 * Created by admin on 11/24/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 11/14/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DB_name = "ichDB";
    public static int DB_Ver = 1;
    Context context;;
    public DatabaseHelper(Context context) {
        super(context, DB_name, null, DB_Ver);
        this.context=context;
    }

    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getInstance(Context context)
    {
        if (instance == null)
            instance = new DatabaseHelper(context);

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(StringQuery.cartMenuQuery);
        db.execSQL(StringQuery.cartMenuOptionCheckboxQuery);
        db.execSQL(StringQuery.cartMenuOptionRadioQuery);
        db.execSQL(StringQuery.cartMenuOptionSelectQuery);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
