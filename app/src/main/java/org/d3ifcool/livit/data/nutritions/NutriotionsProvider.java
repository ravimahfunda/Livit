package org.d3ifcool.livit.data.nutritions;

/**
 * Created by user on 13/04/2018.
 */

import org.d3ifcool.livit.data.nutritions.NutritionsContract;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import org.d3ifcool.livit.data.nutritions.NutritionsContract.NutritionsEntry;
import org.d3ifcool.livit.data.nutritions.NutritionsContract;

/**
 * Created by Praktikan on 27/03/2018.
 */

public class NutriotionsProvider extends ContentProvider {

    private final static int NUTRITIONS = 100;

    private final static int NUTRITIONS_ID = 101;

    public static final String LOG_TAG = NutriotionsProvider.class.getSimpleName();

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(NutritionsContract.CONTENT_AUTHORITY, NutritionsContract.PATH_NUTRITIONS, NUTRITIONS);
        sUriMatcher.addURI(NutritionsContract.CONTENT_AUTHORITY, NutritionsContract.PATH_NUTRITIONS+"/#", NUTRITIONS_ID);
    }

    private NutritionsDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new NutritionsDbHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor;
        int match = sUriMatcher.match(uri);

        switch (match){
            case NUTRITIONS :{
                cursor = db.query(NutritionsEntry.TABLE_NAME, projection,
                        selection, selectionArgs,null,null,sortOrder);
                break;
            }
            case NUTRITIONS_ID : {
                selection = NutritionsEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(NutritionsEntry.TABLE_NAME, projection,
                        selection, selectionArgs,null,null,sortOrder);
                break;
            }
            default:
                throw new IllegalArgumentException("Cannot query unknown UIR" + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    private Uri insertNutritions(Uri uri, ContentValues values) {
        Integer carbs = values.getAsInteger(NutritionsEntry.COLUMN_NUTRITIONS_CARBS);
        if (carbs == null) {
            throw new IllegalArgumentException("Nutritions requires a carbs");
        }

        Integer protein = values.getAsInteger(NutritionsContract.NutritionsEntry.COLUMN_NUTRITIONS_PROTEIN);
        if (protein == null) {
            throw new IllegalArgumentException("Nutritions requires valid protein");
        }

        Integer vegetable = values.getAsInteger(NutritionsContract.NutritionsEntry.COLUMN_NUTRITIONS_VEGETABLE);
        if (vegetable == null) {
            throw new IllegalArgumentException("Nutritions requires valid vegetable");
        }

        Integer milk = values.getAsInteger(NutritionsContract.NutritionsEntry.COLUMN_NUTRITIONS_MILK);
        if (milk == null) {
            throw new IllegalArgumentException("Nutritions requires valid milk");
        }

        Integer fruity = values.getAsInteger(NutritionsContract.NutritionsEntry.COLUMN_NUTRITIONS_FRUITY);
        if (fruity == null) {
            throw new IllegalArgumentException("Nutritions requires valid fruity");
        }
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        long id = db.insert(NutritionsEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case NUTRITIONS:
                return NutritionsEntry.CONTENT_LIST_TYPE;
            case NUTRITIONS_ID:
                return NutritionsEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case NUTRITIONS:
                return insertNutritions(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }
    @Override
    public int delete(@NonNull Uri uri, String selection, @Nullable String[] selectionArgs) {
        // Get writeable database
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case NUTRITIONS:
                // Delete all rows that match the selection and selection args
                return db.delete(NutritionsEntry.TABLE_NAME, selection, selectionArgs);
            case NUTRITIONS_ID:
                // Delete a single row given by the ID in the URI
                selection = NutritionsEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return db.delete(NutritionsEntry.TABLE_NAME, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
    }
    private int updatenNutritions(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (values.containsKey(NutritionsEntry.COLUMN_NUTRITIONS_CARBS)) {
            Integer carbs = values.getAsInteger(NutritionsEntry.COLUMN_NUTRITIONS_CARBS);
            if (carbs == null || !NutritionsEntry.isValidCarbs(carbs)) {
                throw new IllegalArgumentException("Carbs requires valid Carbs");
            }
        }

        if (values.containsKey(NutritionsEntry.COLUMN_NUTRITIONS_PROTEIN)) {
            Integer protein = values.getAsInteger(NutritionsEntry.COLUMN_NUTRITIONS_PROTEIN);
            if (protein == null || !NutritionsEntry.isValidProtein(protein)) {
                throw new IllegalArgumentException("Protein requires valid Protein");
            }
        }

        if (values.containsKey(NutritionsEntry.COLUMN_NUTRITIONS_VEGETABLE)) {
            Integer gender = values.getAsInteger(NutritionsEntry.COLUMN_NUTRITIONS_VEGETABLE);
            if (gender == null || !NutritionsEntry.isValidVegetable(gender)) {
                throw new IllegalArgumentException("Vegetable requires valid Vegetable");
            }
        }

        if (values.containsKey(NutritionsEntry.COLUMN_NUTRITIONS_MILK)) {
            Integer milk = values.getAsInteger(NutritionsEntry.COLUMN_NUTRITIONS_MILK);
            if (milk == null || !NutritionsEntry.isValidMILK(milk)) {
                throw new IllegalArgumentException("Milk requires valid Milk");
            }
        }

        if (values.containsKey(NutritionsEntry.COLUMN_NUTRITIONS_FRUITY)) {
            Integer fruity = values.getAsInteger(NutritionsEntry.COLUMN_NUTRITIONS_FRUITY);
            if (fruity == null || !NutritionsEntry.isValidFRUITY(fruity)) {
                throw new IllegalArgumentException("Fruity requires valid Fruity");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        return db.update(NutritionsEntry.TABLE_NAME, values, selection, selectionArgs);
    }

    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case NUTRITIONS:
                return updatenNutritions(uri, contentValues, selection, selectionArgs);
            case NUTRITIONS_ID:
                selection = NutritionsEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return updatenNutritions(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }
}