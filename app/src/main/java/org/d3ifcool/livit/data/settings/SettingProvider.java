package org.d3ifcool.livit.data.settings;

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

import org.d3ifcool.livit.data.LivitDbHelper;
import org.d3ifcool.livit.data.settings.SettingsContract.SettingsEntry;

/**
 * Created by Multimedia on 12/04/2018.
 */

public class SettingProvider extends ContentProvider {

    public static final String LOG_TAG = SettingProvider.class.getSimpleName();

    private final static int SETTINGS = 100;
    private final static int SETTINGS_ID = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(SettingsContract.CONTENT_AUTHORITY, SettingsContract.PATH_SETTINGS, SETTINGS);
        sUriMatcher.addURI(SettingsContract.CONTENT_AUTHORITY, SettingsContract.PATH_SETTINGS+"/#", SETTINGS_ID);
    }

    private LivitDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new LivitDbHelper(getContext());
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
            case SETTINGS_ID : {
                selection = SettingsContract.SettingsEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(SettingsContract.SettingsEntry.TABLE_NAME, projection,
                        selection, selectionArgs,null,null,sortOrder);
                break;
            }
            default:
                throw new IllegalArgumentException("Cannot query unknown URI" + uri);

        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    private Uri insertSettings(Uri uri, ContentValues values) {

        Integer bloodType = values.getAsInteger(SettingsEntry.COLUMN_SETTINGS_BLOOD_TYPE);
        if (bloodType== null || !SettingsEntry.isValidBloodType(bloodType)) {
            throw new IllegalArgumentException("Settings requires valid blood type");
        }

        Integer goals = values.getAsInteger(SettingsEntry.COLUMN_SETTINGS_GOALS);
        if (goals== null || !SettingsEntry.isValidGoals(goals)) {
            throw new IllegalArgumentException("Settings requires valid goals");
        }

        Integer sleepPattern = values.getAsInteger(SettingsEntry.COLUMN_SETTINGS_SLEEP_PATTERN);
        if (sleepPattern== null || !SettingsEntry.isValidSleepPattern(sleepPattern)) {
            throw new IllegalArgumentException("Settings requires valid sleep pattern");
        }

        Integer height = values.getAsInteger(SettingsEntry.COLUMN_SETTINGS_HEIGHT);
        if (height== null || height <= 0) {
            throw new IllegalArgumentException("Settings requires height");
        }

        Integer weight = values.getAsInteger(SettingsEntry.COLUMN_SETTINGS_WEIGHT);
        if (weight== null || weight <= 0) {
            throw new IllegalArgumentException("Settings requires weight");
        }

        Integer age = values.getAsInteger(SettingsEntry.COLUMN_SETTINGS_AGE);
        if (age== null || age <= 0) {
            throw new IllegalArgumentException("Settings requires age");
        }

        Integer sex = values.getAsInteger(SettingsEntry.COLUMN_SETTINGS_SEX);
        if (sex == null || !SettingsEntry.isValidSex(sex )) {
            throw new IllegalArgumentException("Settings requires valid sex");
        }

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        long id = db.insert(SettingsContract.SettingsEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case SETTINGS_ID:
                return SettingsEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case SETTINGS:
                return insertSettings(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Get writeable database
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case SETTINGS_ID:
                // Delete a single row given by the ID in the URI
                selection = SettingsEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return db.delete(SettingsEntry.TABLE_NAME, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case SETTINGS_ID:
                selection = SettingsEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return updatePet(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private int updatePet(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (values.containsKey(SettingsEntry.COLUMN_SETTINGS_BLOOD_TYPE)) {
            Integer bloodType = values.getAsInteger(SettingsEntry.COLUMN_SETTINGS_BLOOD_TYPE);
            if (bloodType== null || !SettingsEntry.isValidBloodType(bloodType)) {
                throw new IllegalArgumentException("Settings requires valid blood type");
            }
        }

        if (values.containsKey(SettingsEntry.COLUMN_SETTINGS_GOALS)) {
            Integer goals = values.getAsInteger(SettingsEntry.COLUMN_SETTINGS_GOALS);
            if (goals== null || !SettingsEntry.isValidGoals(goals)) {
                throw new IllegalArgumentException("Settings requires valid goals");
            }
        }

        if (values.containsKey(SettingsEntry.COLUMN_SETTINGS_SLEEP_PATTERN)) {
            Integer sleepPattern = values.getAsInteger(SettingsEntry.COLUMN_SETTINGS_SLEEP_PATTERN);
            if (sleepPattern == null || !SettingsEntry.isValidSleepPattern(sleepPattern)) {
                throw new IllegalArgumentException("Settings requires valid sleep pattern");
            }
        }

        if (values.containsKey(SettingsEntry.COLUMN_SETTINGS_HEIGHT)) {
            Integer height = values.getAsInteger(SettingsEntry.COLUMN_SETTINGS_HEIGHT);
            if (height== null || height <= 0) {
                throw new IllegalArgumentException("Settings requires height");
            }
        }

        if (values.containsKey(SettingsEntry.COLUMN_SETTINGS_WEIGHT)) {
            Integer weight = values.getAsInteger(SettingsEntry.COLUMN_SETTINGS_WEIGHT);
            if (weight== null || weight <= 0) {
                throw new IllegalArgumentException("Settings requires weight");
            }
        }

        if (values.containsKey(SettingsEntry.COLUMN_SETTINGS_AGE)) {
            Integer age = values.getAsInteger(SettingsEntry.COLUMN_SETTINGS_AGE);
            if (age== null || age <= 0) {
                throw new IllegalArgumentException("Settings requires age");
            }
        }

        if (values.containsKey(SettingsEntry.COLUMN_SETTINGS_SEX)) {
            Integer sex = values.getAsInteger(SettingsEntry.COLUMN_SETTINGS_SEX);
            if (sex == null || !SettingsEntry.isValidSex(sex )) {
                throw new IllegalArgumentException("Settings requires valid sex");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        return db.update(SettingsEntry.TABLE_NAME, values, selection, selectionArgs);
    }

}

