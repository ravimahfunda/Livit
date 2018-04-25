package org.d3ifcool.livit;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.d3ifcool.livit.data.LivitContract.NutritionsEntry;
import org.d3ifcool.livit.entity.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows user to create a new pet or edit an existing one.
 */
public class NutritionsActivity extends AppCompatActivity implements android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor>{

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

    private int mCarbs = 0;
    private int mProtein = 0;

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

        Button submit = (Button) findViewById(R.id.btnSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertNutritions();
            }
        });
        mCurrentNutritionsUri = getIntent().getData();
        if (mCurrentNutritionsUri == null){
            setTitle(getString(R.string.nutrition_save_nutritions));
            invalidateOptionsMenu();
        }else {
            setTitle(getString(R.string.nutrition_edit_nutritions));
            getSupportLoaderManager().initLoader(1, null,  this);
        }
    }
    private void deleteNutrition(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Dlete Dialog Message");
        builder.setPositiveButton(R.string.action_delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Log.d("NUTRITIONS", "Delete Nutrition Clicked");
                finish();
            }
        });
        builder.setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
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
                    if (selection.equals(getString(R.string.nutritions_carbs_rice))) {
                        mCarbs = 0; // Rice
                    } else if (selection.equals(getString(R.string.nutritions_carbs_bread))) {
                        mCarbs = 1; // Bread
                    } else if (selection.equals(getString(R.string.nutritions_carbs_potato))) {
                        mCarbs = 2; //Potato
                    } else {
                        mCarbs = 3; // Other
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
                    if (selection.equals(getString(R.string.nutritions_protein_lamb))) {
                        mProtein = 0; // Lamb
                    } else if (selection.equals(getString(R.string.nutritions_protein_meat))) {
                        mProtein = 1; // Meat
                    } else if (selection.equals(getString(R.string.nutritions_protein_chicken))){
                        mProtein = 2; // Chicken
                    } else if (selection.equals(getString(R.string.nutritions_protein_fish))){
                        mProtein = 3; // Fish
                    } else if (selection.equals(getString(R.string.nutritions_protein_egg))) {
                        mProtein = 4; // Egg
                    } else {
                        mProtein = 5; // Other
                    }
                }
                System.out.println(">>>>>>>>>>>>>TYPE > "+ mProtein);
            }
            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mProtein = 0; // Unknown
            }
        });
    }
    private void insertNutritions(){
        RadioButton yesVeg = (RadioButton) findViewById(R.id.yesVeg);
        RadioButton yesMilk = (RadioButton) findViewById(R.id.yesMilk);
        RadioButton yesFruity = (RadioButton) findViewById(R.id.yesFruity);

        int isVeg = yesVeg.isChecked() ? 1 : 0;
        int isMilk = yesMilk.isChecked() ? 1 : 0;
        int isFruity = yesFruity.isChecked() ? 1 : 0;

        ContentValues values = new ContentValues();
        values.put(NutritionsEntry.COLUMN_NUTRITIONS_CARBS, mCarbs);
        values.put(NutritionsEntry.COLUMN_NUTRITIONS_PROTEIN, mProtein);
        values.put(NutritionsEntry.COLUMN_NUTRITIONS_VEGETABLE, isVeg);
        values.put(NutritionsEntry.COLUMN_NUTRITIONS_MILK, isMilk);
        values.put(NutritionsEntry.COLUMN_NUTRITIONS_FRUITY, isFruity);

        getContentResolver().insert(NutritionsEntry.CONTENT_URI, values);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu options from the res/menu/menu_editor.xml file.
//        // This adds menu items to the app bar.
//        getMenuInflater().inflate(R.menu.setting_option_menu, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_done:
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                insertNutritions();
                return true;
            case R.id.action_delete:
                deleteNutrition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                NutritionsEntry._ID,
                NutritionsEntry.COLUMN_NUTRITIONS_CARBS,
                NutritionsEntry.COLUMN_NUTRITIONS_PROTEIN,
                NutritionsEntry.COLUMN_NUTRITIONS_VEGETABLE,
                NutritionsEntry.COLUMN_NUTRITIONS_MILK,
                NutritionsEntry.COLUMN_NUTRITIONS_FRUITY,
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
//            mCarbsSpinner.setSelection(data.getInt(data.getColumnIndex(NutritionsEntry.COLUMN_NUTRITIONS_CARBS)));
//            mProteinSpinner.setSelection(data.getInt(data.getColumnIndex(NutritionsEntry.COLUMN_NUTRITIONS_PROTEIN)));
            int carbs = data.getInt(data.getColumnIndex(NutritionsEntry.COLUMN_NUTRITIONS_CARBS));
            switch (carbs){
                case NutritionsEntry.CARBS_RICE:
                    mCarbsSpinner.setSelection(0);
                    break;

                case NutritionsEntry.CARBS_BREAD:
                    mCarbsSpinner.setSelection(1);
                    break;

                case NutritionsEntry.CARBS_POTATO:
                    mCarbsSpinner.setSelection(2);
                    break;

                default:
                    mCarbsSpinner.setSelection(3);
            }
            int protein = data.getInt(data.getColumnIndex(NutritionsEntry.COLUMN_NUTRITIONS_CARBS));
            switch (protein){
                case NutritionsEntry.PROTEIN_LAMB:
                    mProteinSpinner.setSelection(0);
                    break;
                case NutritionsEntry.PROTEIN_MEAT:
                    mProteinSpinner.setSelection(1);
                    break;
                case NutritionsEntry.PROTEIN_CHICKEN:
                    mProteinSpinner.setSelection(2);
                    break;
                case NutritionsEntry.PROTEIN_FISH:
                    mProteinSpinner.setSelection(3);
                    break;
                case NutritionsEntry.PROTEIN_EGG:
                    mProteinSpinner.setSelection(4);
                    break;
                default:
                    mProteinSpinner.setSelection(5);
            }
//            mVegetableRadioButton.setText(data.getInt(data.getColumnIndex(NutritionsEntry.COLUMN_NUTRITIONS_VEGETABLE)));
//            mMilkRadioButton.setText(data.getString(data.getColumnIndex(NutritionsEntry.COLUMN_NUTRITIONS_MILK)));
//            mFruityRadioButton.setText(data.getString(data.getColumnIndex(NutritionsEntry.COLUMN_NUTRITIONS_FRUITY)));
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCarbsSpinner.setSelection(0);
        mProteinSpinner.setSelection(0);
    }
}
