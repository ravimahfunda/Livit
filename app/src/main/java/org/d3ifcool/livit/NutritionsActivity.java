package org.d3ifcool.livit;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import org.d3ifcool.livit.data.nutritions.NutritionsContract;
import org.d3ifcool.livit.data.settings.SettingsContract;

/**
 * Allows user to create a new pet or edit an existing one.
 */
public class NutritionsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    /** EditText field to enter the nutritions's Carbs */
    private Spinner mCarbsSpinner;

    /** EditText field to enter the nutritions's Protein */
    private Spinner mProteinSpinner;

    /** EditText field to enter the nutritions's Vegetable */
    private RadioButton mVegetableRadioButton;

    /** EditText field to enter the nutritions's Milk */
    private RadioButton mMilkRadioButton;

    /** EditText field to enter the nutritions's Fruity */
    private RadioButton mFruityRadioButton;

    private Uri mCurrentNutritionsUri;

    /**
     * Gender of the pet. The possible values are:
     * 0 for unknown gender, 1 for male, 2 for female.
     */
    private int mCarbs = 0;
    private int mProtein = 0;
    private int mVegetable =0 ;
    private int mMilk =0 ;
    private int mFruity =0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutritions);

        // Find all relevant views that we will need to read user input from

        mCarbsSpinner = (Spinner) findViewById(R.id.carbs);
        mProteinSpinner = (Spinner) findViewById(R.id.protein);
        mVegetableRadioButton = (RadioButton) findViewById(R.id.yesVeg);
        mVegetableRadioButton = (RadioButton) findViewById(R.id.noVeg);
        mMilkRadioButton = (RadioButton) findViewById(R.id.yesMilk);
        mMilkRadioButton = (RadioButton) findViewById(R.id.noMilk);
        mFruityRadioButton = (RadioButton) findViewById(R.id.yesFruity);
        mFruityRadioButton = (RadioButton) findViewById(R.id.noFruity);

        setupSpinner();

        mCurrentNutritionsUri = getIntent().getData();
    }
    /**
     * Setup the dropdown spinner that allows the user to select the gender of the pet.
     */
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout

        ArrayAdapter carbsSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_nutritions_carbs_options, android.R.layout.simple_spinner_item);

        ArrayAdapter proteinSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_nutritions_protein_options, android.R.layout.simple_spinner_item);
//
//
//        // Specify dropdown layout style - simple list view with 1 item per line
        carbsSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        proteinSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mCarbsSpinner.setAdapter(carbsSpinnerAdapter);
        mProteinSpinner.setAdapter(proteinSpinnerAdapter);
        mVegetableRadioButton.isChecked();
        mMilkRadioButton.isChecked();
        mFruityRadioButton.isChecked();

        // Set the integer mSelected to the constant values
        mCarbsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals("Rice")) {
                        mCarbs = 1; // Male
                    } else if (selection.equals("Bread")) {
                        mCarbs = 2; // Female
                    } else if (selection.equals("Potato")) {
                        mCarbs = 3; // Female
                    } else {
                        mCarbs = 0; // Unknown
                    }
                }
            }
            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mCarbs = 0; // Unknown
            }
        });
        mProteinSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals("Ikan")) {
                        mProtein = 1; // Ikan
                    } else if (selection.equals("Telur")) {
                        mProtein = 2; // Telur
                    } else {
                        mProtein = 0; // Unknown
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mProtein = 0; // Unknown
            }
        });
        RadioButton yesVeg = (RadioButton) findViewById(R.id.yesVeg);
        RadioButton noVeg = (RadioButton) findViewById(R.id.noVeg);

        if (yesVeg.isChecked()){
            mVegetableRadioButton = yesVeg;
        }else {
            mVegetableRadioButton = noVeg;
        }

        RadioButton yesMilk = (RadioButton) findViewById(R.id.yesMilk);
        RadioButton noMilk = (RadioButton) findViewById(R.id.noMilk);

        if (yesMilk.isChecked()){
            mMilkRadioButton = yesMilk;
        }else {
            mVegetableRadioButton = noMilk;
        }

        RadioButton yesFruity = (RadioButton) findViewById(R.id.yesFruity);
        RadioButton noFruity = (RadioButton) findViewById(R.id.noFruity);

        if (yesFruity.isChecked()){
            mFruityRadioButton = yesFruity;
        }else {
            mFruityRadioButton = noFruity;
        }
    }
    private void insertNutritions(){

        ContentValues values = new ContentValues();
        values.put(NutritionsContract.NutritionsEntry.COLUMN_NUTRITIONS_CARBS, NutritionsContract.NutritionsEntry.COLUMN_NUTRITIONS_CARBS);
        values.put(NutritionsContract.NutritionsEntry.COLUMN_NUTRITIONS_PROTEIN, NutritionsContract.NutritionsEntry.COLUMN_NUTRITIONS_PROTEIN);
        values.put(NutritionsContract.NutritionsEntry.COLUMN_NUTRITIONS_VEGETABLE, NutritionsContract.NutritionsEntry.COLUMN_NUTRITIONS_VEGETABLE);
        values.put(NutritionsContract.NutritionsEntry.COLUMN_NUTRITIONS_MILK, 170);
        values.put(NutritionsContract.NutritionsEntry.COLUMN_NUTRITIONS_FRUITY, 47);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>> "+ getContentResolver().insert(SettingsContract.SettingsEntry.CONTENT_URI, values));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.setting_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_done:
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                insertNutritions();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                NutritionsContract.NutritionsEntry._ID,
                NutritionsContract.NutritionsEntry.COLUMN_NUTRITIONS_CARBS,
                NutritionsContract.NutritionsEntry.COLUMN_NUTRITIONS_PROTEIN,
                NutritionsContract.NutritionsEntry.COLUMN_NUTRITIONS_VEGETABLE,
                NutritionsContract.NutritionsEntry.COLUMN_NUTRITIONS_MILK,
                NutritionsContract.NutritionsEntry.COLUMN_NUTRITIONS_FRUITY,
        };

        return new android.support.v4.content.CursorLoader(
                this,
                mCurrentNutritionsUri,
                projection,
                null,
                null,
                null);
    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data.moveToFirst()){
            mCarbsSpinner.setSelection(data.getInt(data.getColumnIndex(NutritionsContract.NutritionsEntry.COLUMN_NUTRITIONS_CARBS)));
            mProteinSpinner.setSelection(data.getInt(data.getColumnIndex(NutritionsContract.NutritionsEntry.COLUMN_NUTRITIONS_PROTEIN)));
            mVegetableRadioButton.setText(data.getInt(data.getColumnIndex(NutritionsContract.NutritionsEntry.COLUMN_NUTRITIONS_VEGETABLE)));
            mMilkRadioButton.setText(data.getString(data.getColumnIndex(NutritionsContract.NutritionsEntry.COLUMN_NUTRITIONS_MILK)));
            mFruityRadioButton.setText(data.getString(data.getColumnIndex(NutritionsContract.NutritionsEntry.COLUMN_NUTRITIONS_FRUITY)));
            }
    }
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCarbsSpinner.setSelection(0);
        mProteinSpinner.setSelection(0);
        mVegetableRadioButton.setText(0);
        mMilkRadioButton.setText(0);
        mFruityRadioButton.setText(0);
    }
}

//    RadioButton veg,noVeg,milk, noMilk, fruity, noFruity;
//    int checkedRadioButton = 0;
//    private RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
//        @Override
//        public void onCheckedChanged(RadioGroup group, int checkedId) {
//            switch (checkedId){
//                case R.id.yesVeg:
//                    checkedRadioButton = 1;
//                    break;
//                case R.id.noVeg:
//                    checkedRadioButton = 2;
//                    break;
//                case R.id.yesMilk:
//                    checkedRadioButton = 3;
//                    break;
//                case R.id.noMilk:
//                    checkedRadioButton = 4;
//                    break;
//                case R.id.yesFruity:
//                    checkedRadioButton = 5;
//                    break;
//                case R.id.noFruity:
//                    checkedRadioButton = 6;
//                    break;
//                default:
//            }
//        }
//    };
//    private View.OnClickListener onClickListenerSubmit = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Toast.makeText(NutritionsActivity.this, getOption(), Toast.LENGTH_SHORT).show();
//        }
//    };
//    private View.OnClickListener onClickListenerReset = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            veg.setChecked(false);
//            noVeg.setChecked(false);
//            milk.setChecked(false);
//            noMilk.setChecked(false);
//            fruity.setChecked(false);
//            noFruity.setChecked(false);
//            checkedRadioButton = 0;
//        }
//    };
//    private String getOption(){
//        if(checkedRadioButton == 0){
//            return "Tidak ada option yang dipilih";
//        }else if(checkedRadioButton == 1){
//            return "Vegetable";
//        }else if(checkedRadioButton == 2){
//            return "No Vegetable";
//        }else if(checkedRadioButton == 3){
//            return "Milk";
//        }else if(checkedRadioButton == 4){
//            return "No Milk";
//        }else if(checkedRadioButton == 5){
//            return "Fruity";
//        }else if(checkedRadioButton == 6){
//            return "No Fruity";
//        }else
//            return "Option salah";
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_nutritions)//
//    }
