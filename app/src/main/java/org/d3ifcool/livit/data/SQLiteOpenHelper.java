package org.d3ifcool.livit.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ACER on 25/03/2018.
 */

public abstract class SQLiteOpenHelper {

    public SQLiteOpenHelper(Context context, String databaseName, Object o, int databaseVersion) {

    }

    public abstract void onCreate(SQLiteDatabase db);

    public abstract void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1);
}
