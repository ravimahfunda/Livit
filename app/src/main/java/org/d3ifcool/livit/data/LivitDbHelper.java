package org.d3ifcool.livit.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.d3ifcool.livit.data.settings.SettingsContract.SettingsEntry;

/**
 * Created by Multimedia on 12/04/2018.
 */

public class LivitDbHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "livit.db";
    private static final int DATABASE_VERSION = 1;

    public LivitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_SETTINGS_TABLE = "CREATE TABLE " + SettingsEntry.TABLE_NAME + " (" +
                SettingsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SettingsEntry.COLUMN_SETTINGS_BLOOD_TYPE+" INTEGER NOT NULL, "+
                SettingsEntry.COLUMN_SETTINGS_GOALS+" INTEGER NOT NULL, "+
                SettingsEntry.COLUMN_SETTINGS_SLEEP_PATTERN+" INTEGER NOT NULL, "+
                SettingsEntry.COLUMN_SETTINGS_HEIGHT+" INTEGER NOT NULL, "+
                SettingsEntry.COLUMN_SETTINGS_WEIGHT+" INTEGER NOT NULL, "+
                SettingsEntry.COLUMN_SETTINGS_AGE+" INTEGER NOT NULL, "+
                SettingsEntry.COLUMN_SETTINGS_SEX+" INTEGER NOT NULL "+
                ");";
        db.execSQL(SQL_CREATE_SETTINGS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //The database is still at version 1,
        //There is nothing to do here.
    }

}
