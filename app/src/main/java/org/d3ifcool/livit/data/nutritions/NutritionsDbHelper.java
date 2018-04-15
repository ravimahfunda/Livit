package org.d3ifcool.livit.data.nutritions;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.d3ifcool.livit.data.nutritions.NutritionsContract.NutritionsEntry;
/**
 * Created by user on 13/04/2018.
 */

public class NutritionsDbHelper extends android.database.sqlite.SQLiteOpenHelper {
    private static final String DATABASE_NAME = "shelter.db";
    private static final int DATABASE_VERSION = 1;

    public NutritionsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_NUTRITIONS_TABLE = "CREATE TABLE " + NutritionsContract.NutritionsEntry.TABLE_NAME + " (" +
                NutritionsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NutritionsEntry.COLUMN_NUTRITIONS_CARBS+" INTEGER NOT NULL, "+
                NutritionsEntry.COLUMN_NUTRITIONS_PROTEIN+" INTEGER NOT NULL, "+
                NutritionsEntry.COLUMN_NUTRITIONS_VEGETABLE+" INTEGER NOT NULL, "+
                NutritionsEntry.COLUMN_NUTRITIONS_MILK+" INTEGER NOT NULL, "+
                NutritionsEntry.COLUMN_NUTRITIONS_FRUITY+" INTEGER NOT NULL "+
                ");";
        db.execSQL(SQL_CREATE_NUTRITIONS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //The database is still at version 1,
        //There is nothing to do here.
    }
}
