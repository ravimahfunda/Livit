package org.d3ifcool.livit.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Multimedia on 12/04/2018.
 */

public class LivitContract {


    public final static String CONTENT_AUTHORITY = "org.d3ifcool.livit";
    public final static Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);
    public final static String PATH_SETTINGS= "settings";
    public final static String PATH_ACHIEVEMENTS = "achievements";
    public final static String PATH_EXERCISES= "exercises";

    private LivitContract() {}

    public static final class SettingsEntry implements BaseColumns {

        public final static Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_SETTINGS);
        public final static String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE +
                "/" + CONTENT_AUTHORITY + "/" + PATH_SETTINGS;
        public final static String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE+
                "/" + CONTENT_AUTHORITY + "/" + PATH_SETTINGS;

        public final static String TABLE_NAME = "settings";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_SETTINGS_BLOOD_TYPE= "blood_type";
        public final static String COLUMN_SETTINGS_GOALS = "goals";
        public final static String COLUMN_SETTINGS_SLEEP_PATTERN = "sleep_pattern";

        public final static String COLUMN_SETTINGS_HEIGHT = "height";
        public final static String COLUMN_SETTINGS_WEIGHT = "weight";
        public final static String COLUMN_SETTINGS_AGE = "age";
        public final static String COLUMN_SETTINGS_SEX = "sex";

        public final static int SEX_MALE = 0;
        public final static int SEX_FEMALE = 1;

        public final static int GOALS_CONDITIONING = 0;
        public final static int GOALS_MUSCLE_MASS = 1;
        public final static int GOALS_DIET = 2;

        public final static int SLEEP_PATTERN_NORMAL = 0;
        public final static int SLEEP_PATTERN_POLY_PHASIC= 1;

        public final static int BLOOD_TYPE_A = 0;
        public final static int BLOOD_TYPE_B = 1;
        public final static int BLOOD_TYPE_O = 2;
        public final static int BLOOD_TYPE_AB = 3;


        public static boolean isValidSex(Integer gender){
            switch (gender){
                case SEX_MALE:
                case SEX_FEMALE:{
                    return true;
                }
                default:{
                    return false;
                }
            }
        }

        public static boolean isValidGoals(Integer goals){
            switch (goals){
                case GOALS_CONDITIONING:
                case GOALS_MUSCLE_MASS:
                case GOALS_DIET:{
                    return true;
                }
                default:{
                    return false;
                }
            }
        }

        public static boolean isValidBloodType(Integer bloodType){
            switch (bloodType){
                case BLOOD_TYPE_A:
                case BLOOD_TYPE_B:
                case BLOOD_TYPE_O:
                case BLOOD_TYPE_AB:{
                    return true;
                }
                default:{
                    return false;
                }
            }
        }

//        FIX THIS LATER
        public static boolean isValidSleepPattern(Integer sleepPattern){
            switch (sleepPattern){
                case SLEEP_PATTERN_NORMAL:
                case SLEEP_PATTERN_POLY_PHASIC: {
                    return true;
                }
                default:{
                    return false;
                }
            }
        }

    }

    public static final class AchievementsEntry implements BaseColumns {

        public final static Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_ACHIEVEMENTS);
        public final static String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE +
                "/" + CONTENT_AUTHORITY + "/" + PATH_ACHIEVEMENTS;
        public final static String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE+
                "/" + CONTENT_AUTHORITY + "/" + PATH_ACHIEVEMENTS;

        public final static String TABLE_NAME = "achievements";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_ACHIEVEMENTS_TITLE= "title";
        public final static String COLUMN_ACHIEVEMENTS_DESCRIPTION = "description";
        public final static String COLUMN_ACHIEVEMENTS_PROGRESS = "progress";
        public final static String COLUMN_ACHIEVEMENTS_TARGET = "target";
        public final static String COLUMN_ACHIEVEMENTS_CATEGORY = "category";

        public final static int TYPE_EXERCISES = 0;
        public final static int TYPE_NUTRITIONS = 1;


        public static boolean isType(Integer type){
            switch (type){
                case TYPE_EXERCISES:
                case TYPE_NUTRITIONS:{
                    return true;
                }
                default:{
                    return false;
                }
            }
        }

    }

    public static final class ExercisessEntry implements BaseColumns {

        public final static Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_EXERCISES);
        public final static String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE +
                "/" + CONTENT_AUTHORITY + "/" + PATH_EXERCISES;
        public final static String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE+
                "/" + CONTENT_AUTHORITY + "/" + PATH_EXERCISES;

        public final static String TABLE_NAME = "exercises";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_ACHIEVEMNTS_DATE_TIME= "date_time";
        public final static String COLUMN_ACHIEVEMNTS_DURATION= "duration";
        public final static String COLUMN_ACHIEVEMNTS_TRACK= "track";
        public final static String COLUMN_ACHIEVEMNTS_AVERAGE_SPEED = "average_speed";
        public final static String COLUMN_ACHIEVEMNTS_CALORIES_BURNED= "calories_burned";

    }

}

