package org.d3ifcool.livit.data.nutritions;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by user on 13/04/2018.
 */

public final class NutritionsContract {
    public final static String CONTENT_AUTHORITY = "org.d3ifcool.livit.data.nutritions";
    public final static Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);
    public final static String PATH_NUTRITIONS = "nutritions";

    private NutritionsContract() {}

    public static final class NutritionsEntry implements BaseColumns {

        //        TAMBAHIN INI
        public final static Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NUTRITIONS);
        public final static String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE +
                "/" + CONTENT_AUTHORITY + "/" + PATH_NUTRITIONS;
        public final static String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE+
                "/" + CONTENT_AUTHORITY + "/" + PATH_NUTRITIONS;

        public final static String TABLE_NAME = "nutritions";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_NUTRITIONS_CARBS = "carbs";
        public final static String COLUMN_NUTRITIONS_PROTEIN= "protein";
        public final static String COLUMN_NUTRITIONS_VEGETABLE= "vegetable";
        public final static String COLUMN_NUTRITIONS_MILK= "milk";
        public final static String COLUMN_NUTRITIONS_FRUITY= "fruity";

        public final static int CARBS_UNKNOWN = 0;
        public final static int CARBS_RICE = 1;
        public final static int CARBS_BREAD = 2;
        public final static int CARBS_POTATO = 3;
        public final static int CARBS_OTHER = 4;

        public static boolean isValidCarbs(Integer carbs){
            switch (carbs){
                case CARBS_UNKNOWN :
                case CARBS_RICE:
                case CARBS_BREAD:
                case CARBS_POTATO:
                case CARBS_OTHER:{
                    return true;
                }
                default:{
                    return false;
                }
            }
        }

        public final static int PROTEIN_UNKNOWN = 0;
        public final static int PROTEIN_LAMB = 1;
        public final static int PROTEIN_MEAT = 2;
        public final static int PROTEIN_CHICKEN = 3;
        public final static int PROTEIN_FISH = 4;
        public final static int PROTEIN_EGG = 5;
        public final static int PROTEIN_OTHER = 6;

        public static boolean isValidProtein(Integer protein){
            switch (protein){
                case PROTEIN_UNKNOWN :
                case PROTEIN_LAMB:
                case PROTEIN_MEAT:
                case PROTEIN_CHICKEN:
                case PROTEIN_FISH:
                case PROTEIN_EGG:
                case PROTEIN_OTHER:{
                    return true;
                }
                default:{
                    return false;
                }
            }
        }

        public final static int VEGETABLE_UNKNOWN = 0;
        public final static int VEGETABLE_YES = 1;
        public final static int VEGETABLE_NO = 2;

        public static boolean isValidVegetable(Integer vegetable){
            switch (vegetable){
                case VEGETABLE_UNKNOWN :
                case VEGETABLE_YES:
                case VEGETABLE_NO:{
                    return true;
                }
                default:{
                    return false;
                }
            }
        }
        public final static int MILK_UNKNOWN = 0;
        public final static int MILK_YES = 1;
        public final static int MILK_NO = 2;

        public static boolean isValidMILK(Integer gender){
            switch (gender){
                case MILK_UNKNOWN :
                case MILK_YES:
                case MILK_NO:{
                    return true;
                }
                default:{
                    return false;
                }
            }
        }
        public final static int FRUITY_UNKNOWN = 0;
        public final static int FRUITY_YES = 1;
        public final static int FRUITY_NO = 2;

        public static boolean isValidFRUITY(Integer gender){
            switch (gender){
                case FRUITY_UNKNOWN :
                case FRUITY_YES:
                case FRUITY_NO:{
                    return true;
                }
                default:{
                    return false;
                }
            }
        }
    }
}
