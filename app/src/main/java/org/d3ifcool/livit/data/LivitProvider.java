package org.d3ifcool.livit.data;

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

/**
 * Created by Multimedia on 12/04/2018.
 */

public class LivitProvider extends ContentProvider {

    public static final String LOG_TAG = LivitProvider.class.getSimpleName();

    private final static int SETTINGS = 100;
    private final static int SETTINGS_ID = 101;

    private final static int ACHIEVEMENTS = 200;
    private final static int ACHIEVEMENTS_ID = 201;

    private final static int EXERCISES = 300;
    private final static int EXERCISES_ID = 301;

    private final static int NUTRITIONS = 400;
    private final static int NUTRITIONS_ID = 401;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(LivitContract.CONTENT_AUTHORITY, LivitContract.PATH_SETTINGS, SETTINGS);
        sUriMatcher.addURI(LivitContract.CONTENT_AUTHORITY, LivitContract.PATH_SETTINGS+"/#", SETTINGS_ID);

        sUriMatcher.addURI(LivitContract.CONTENT_AUTHORITY, LivitContract.PATH_ACHIEVEMENTS, ACHIEVEMENTS);
        sUriMatcher.addURI(LivitContract.CONTENT_AUTHORITY, LivitContract.PATH_ACHIEVEMENTS+"/#", ACHIEVEMENTS_ID);

        sUriMatcher.addURI(LivitContract.CONTENT_AUTHORITY, LivitContract.PATH_EXERCISES, EXERCISES);
        sUriMatcher.addURI(LivitContract.CONTENT_AUTHORITY, LivitContract.PATH_EXERCISES+"/#", EXERCISES_ID);

        sUriMatcher.addURI(LivitContract.CONTENT_AUTHORITY, LivitContract.PATH_NUTRITIONS, NUTRITIONS);
        sUriMatcher.addURI(LivitContract.CONTENT_AUTHORITY, LivitContract.PATH_NUTRITIONS+"/#", NUTRITIONS_ID);
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
            case ACHIEVEMENTS : {
                cursor = db.query(LivitContract.AchievementsEntry.TABLE_NAME, projection,
                        selection, selectionArgs,null,null,sortOrder);
                break;
            }
            case EXERCISES: {
                cursor = db.query(LivitContract.ExercisessEntry.TABLE_NAME, projection,
                        selection, selectionArgs,null,null,sortOrder);
                break;
            }
            case NUTRITIONS: {
                cursor = db.query(LivitContract.NutritionsEntry.TABLE_NAME, projection,
                        selection, selectionArgs,null,null,sortOrder);
                break;
            }
            case NUTRITIONS_ID: {
                selection = LivitContract.NutritionsEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(LivitContract.NutritionsEntry.TABLE_NAME, projection,
                        selection, selectionArgs,null,null,sortOrder);
                break;
            }
            default:
                throw new IllegalArgumentException("Cannot query unknown URI" + uri);

        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case ACHIEVEMENTS:
                return insertAchievements(uri, contentValues);
            case EXERCISES:
                return insertExercises(uri, contentValues);
            case NUTRITIONS:
                return insertNutritions(uri, contentValues);
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
            case NUTRITIONS_ID:
                // Delete a single row given by the ID in the URI
                selection = LivitContract.NutritionsEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                int deleted = db.delete(LivitContract.NutritionsEntry.TABLE_NAME, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                return deleted;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case NUTRITIONS_ID:
                selection = LivitContract.NutritionsEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return updateNutritions(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private Uri insertAchievements(Uri uri, ContentValues values){

        String title = values.getAsString(LivitContract.AchievementsEntry.COLUMN_ACHIEVEMENTS_TITLE);
        if (title == null || title.equalsIgnoreCase("")) {
            throw new IllegalArgumentException("Achievements requires valid title");
        }

        String subtitle = values.getAsString(LivitContract.AchievementsEntry.COLUMN_ACHIEVEMENTS_DESCRIPTION);
        if (subtitle== null || subtitle.equalsIgnoreCase("")) {
            throw new IllegalArgumentException("Achievements requires valid subtitle");
        }

        Integer target = values.getAsInteger(LivitContract.AchievementsEntry.COLUMN_ACHIEVEMENTS_TARGET);
        if (target == null || target <= 0) {
            throw new IllegalArgumentException("Achievements requires valid target");
        }

        Integer type = values.getAsInteger(LivitContract.AchievementsEntry.COLUMN_ACHIEVEMENTS_CATEGORY);
        if (type == null || !LivitContract.AchievementsEntry.isType(type)) {
            throw new IllegalArgumentException("Achievements requires valid target");
        }

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        long id = db.insert(LivitContract.AchievementsEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    private Uri insertExercises(Uri uri, ContentValues values){

        Long dateTime = values.getAsLong(LivitContract.ExercisessEntry.COLUMN_ACHIEVEMNTS_DATE_TIME);
        if (dateTime == null) {
            throw new IllegalArgumentException("Exercises requires valid date time");
        }

        Integer duration = values.getAsInteger(LivitContract.ExercisessEntry.COLUMN_ACHIEVEMNTS_DURATION);
        if (duration == null|| duration <=0) {
            throw new IllegalArgumentException("Exercises requires valid duration");
        }

        Integer track = values.getAsInteger(LivitContract.ExercisessEntry.COLUMN_ACHIEVEMNTS_TRACK);
        if (track == null|| track <=0) {
            throw new IllegalArgumentException("Exercises requires valid track");
        }

        Integer averageSpeed = values.getAsInteger(LivitContract.ExercisessEntry.COLUMN_ACHIEVEMNTS_AVERAGE_SPEED);
        if (averageSpeed== null|| averageSpeed <=0) {
            throw new IllegalArgumentException("Exercises requires valid average speed");
        }

        Integer caloriesBurned = values.getAsInteger(LivitContract.ExercisessEntry.COLUMN_ACHIEVEMNTS_CALORIES_BURNED);
        if (caloriesBurned == null|| caloriesBurned<=0) {
            throw new IllegalArgumentException("Exercises requires valid calories burned");
        }

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        long id = db.insert(LivitContract.ExercisessEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    private Uri insertNutritions(Uri uri, ContentValues values) {
        Integer carbs = values.getAsInteger(LivitContract.NutritionsEntry.COLUMN_NUTRITIONS_CARBS);
        if (carbs == null || !LivitContract.NutritionsEntry.isValidCarbs(carbs)) {
            throw new IllegalArgumentException("Nutritions requires a carbs");
        }

        Integer protein = values.getAsInteger(LivitContract.NutritionsEntry.COLUMN_NUTRITIONS_PROTEIN);
        if (protein == null || !LivitContract.NutritionsEntry.isValidProtein(protein)) {
            throw new IllegalArgumentException("Nutritions requires valid protein");
        }

        Integer vegetable = values.getAsInteger(LivitContract.NutritionsEntry.COLUMN_NUTRITIONS_VEGETABLE);
        if (vegetable == null || !LivitContract.NutritionsEntry.isValidVegetable(vegetable)) {
            throw new IllegalArgumentException("Nutritions requires valid vegetable");
        }

        Integer milk = values.getAsInteger(LivitContract.NutritionsEntry.COLUMN_NUTRITIONS_MILK);
        if (milk == null || !LivitContract.NutritionsEntry.isValidMilk(milk)) {
            throw new IllegalArgumentException("Nutritions requires valid milk");
        }

        Integer fruity = values.getAsInteger(LivitContract.NutritionsEntry.COLUMN_NUTRITIONS_FRUITY);
        if (fruity == null || !LivitContract.NutritionsEntry.isValidFruity(fruity)) {
            throw new IllegalArgumentException("Nutritions requires valid fruity");
        }

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        long id = db.insert(LivitContract.NutritionsEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    private int updateNutritions(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        if (values.containsKey(LivitContract.NutritionsEntry.COLUMN_NUTRITIONS_CARBS)) {
            Integer carbs = values.getAsInteger(LivitContract.NutritionsEntry.COLUMN_NUTRITIONS_CARBS);
            if (carbs == null || !LivitContract.NutritionsEntry.isValidCarbs(carbs)) {
                throw new IllegalArgumentException("Nutrtion requires valid carbs");
            }
        }

        if (values.containsKey(LivitContract.NutritionsEntry.COLUMN_NUTRITIONS_PROTEIN)) {
            Integer protein = values.getAsInteger(LivitContract.NutritionsEntry.COLUMN_NUTRITIONS_PROTEIN);
            if (protein == null || !LivitContract.NutritionsEntry.isValidProtein(protein)) {
                throw new IllegalArgumentException("Nutrtion requires valid protein");
            }
        }

        if (values.containsKey(LivitContract.NutritionsEntry.COLUMN_NUTRITIONS_VEGETABLE)) {
            Integer veg = values.getAsInteger(LivitContract.NutritionsEntry.COLUMN_NUTRITIONS_VEGETABLE);
            if (veg == null || !LivitContract.NutritionsEntry.isValidVegetable(veg)) {
                throw new IllegalArgumentException("Nutrtion requires valid veggies");
            }
        }

        if (values.containsKey(LivitContract.NutritionsEntry.COLUMN_NUTRITIONS_MILK)) {
            Integer milk = values.getAsInteger(LivitContract.NutritionsEntry.COLUMN_NUTRITIONS_MILK);
            if (milk == null || !LivitContract.NutritionsEntry.isValidMilk(milk)) {
                throw new IllegalArgumentException("Nutrtion requires valid milk");
            }
        }

        if (values.containsKey(LivitContract.NutritionsEntry.COLUMN_NUTRITIONS_FRUITY)) {
            Integer fruity = values.getAsInteger(LivitContract.NutritionsEntry.COLUMN_NUTRITIONS_FRUITY);
            if (fruity == null || !LivitContract.NutritionsEntry.isValidFruity(fruity)) {
                throw new IllegalArgumentException("Nutrtion requires valid fruity");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        getContext().getContentResolver().notifyChange(uri, null);

        return db.update(LivitContract.NutritionsEntry.TABLE_NAME, values, selection, selectionArgs);
    }

}

