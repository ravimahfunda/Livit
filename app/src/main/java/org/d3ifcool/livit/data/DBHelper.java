package org.d3ifcool.livit.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ACER on 25/03/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "user";
    public static final String COLUMN_displayName = "displayName";
    public static final String COLUMN_email = "email";
    public static final String COLUMN_username = "username";
    public static final String COLUMN_blodType = "blodType";
    public static final String COLUMN_goals = "goals";
    public static final String COLUMN_height = "height";
    public static final String COLUMN_weight = "weight";
    public static final String COLUMN_age = "age";
    public static final String COLUMN_sex = "sex";
    private static final String db_name ="user.db";
    private static final int db_version=1;

    private static final String db_create = "create table "
            + TABLE_NAME + "("
            + COLUMN_displayName +" varchar(50) not null, "
            + COLUMN_email+ " varchar(50) not null, "
            + COLUMN_username+ " varchar(50) not null, "
            + COLUMN_blodType+ " varchar(50) not null, "
            + COLUMN_goals+ " varchar(50) not null, "
            + COLUMN_height+ " int (3) not null, "
            + COLUMN_weight+ " int(3) not null, "
            + COLUMN_age+ " int(3) not null, "
            + COLUMN_sex+ " varchar() not null);";

    public DBHelper(Context context) {
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(db_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),"Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
