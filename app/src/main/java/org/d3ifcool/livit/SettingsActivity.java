package org.d3ifcool.livit;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.d3ifcool.livit.data.LivitDbHelper;
import org.d3ifcool.livit.data.settings.SettingsContract.SettingsEntry;

public class SettingsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private LivitDbHelper mDbHelper;
    private Uri mCurrentPetUri;


    private Spinner mBloodTypeSpinner;
    private Spinner mGoalsSpinner;
    private Spinner mSleepPatternSpinner;
    private EditText mHeightEditText;
    private EditText mWeightEditText;
    private EditText mAgeEditText;
    private Spinner mSexSpinner;

    private int mBloodType= 0;
    private int mGoals= 0;
    private int mSleepPattern= 0;
    private int mSex= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Creating user data
        User user = new User("O", "Diet" ,"170cm" ,"47kg" , "16", "Female");

        //so the text can be edited according to id

        mBloodTypeSpinner = (Spinner) findViewById(R.id.blood_type);
        mGoalsSpinner = (Spinner) findViewById(R.id.goals);
        mSleepPatternSpinner = (Spinner) findViewById(R.id.sleep_pattern);
        mHeightEditText = (EditText) findViewById(R.id.your_height);
        mWeightEditText = (EditText) findViewById(R.id.your_weight);
        mAgeEditText = (EditText) findViewById(R.id.your_age);
        mSexSpinner = (Spinner) findViewById(R.id.your_sex);

        setupSpinner();

        mCurrentPetUri = getIntent().getData();
        getSupportLoaderManager().initLoader(1,null,this);
    }

    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter bloodTypeSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_blood_type_options, android.R.layout.simple_spinner_item);

        ArrayAdapter goalsSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_goals_options, android.R.layout.simple_spinner_item);

        ArrayAdapter sleepPatternSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_sleep_pattern_options, android.R.layout.simple_spinner_item);

        ArrayAdapter sexSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_sex_options, android.R.layout.simple_spinner_item);


        // Specify dropdown layout style - simple list view with 1 item per line
        bloodTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        goalsSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sleepPatternSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sexSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mBloodTypeSpinner.setAdapter(bloodTypeSpinnerAdapter);
        mGoalsSpinner.setAdapter(goalsSpinnerAdapter);
        mSleepPatternSpinner.setAdapter(sleepPatternSpinnerAdapter);
        mSexSpinner.setAdapter(sexSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mBloodTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.blood_type_B))) {
                        mBloodType = SettingsEntry.BLOOD_TYPE_B; // B
                    }else if (selection.equals(getString(R.string.blood_type_O))) {
                        mBloodType = SettingsEntry.BLOOD_TYPE_O; // O
                    }else if (selection.equals(getString(R.string.blood_type_AB))) {
                        mBloodType = SettingsEntry.BLOOD_TYPE_AB; // AB
                    } else {
                        mBloodType = SettingsEntry.BLOOD_TYPE_A; // A
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mBloodType = SettingsEntry.BLOOD_TYPE_A; // A
            }
        });
        mGoalsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.goals_muscle_mass))) {
                        mGoals = SettingsEntry.GOALS_MUSCLE_MASS; // MUSCLE MASS
                    }else if (selection.equals(getString(R.string.goals_diet))) {
                        mGoals = SettingsEntry.GOALS_DIET; // DIET
                    } else {
                        mGoals = SettingsEntry.GOALS_CONDITIONING; // CONDITIONING
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGoals = SettingsEntry.GOALS_CONDITIONING; // CONDITIONING
            }
        });
        mSleepPatternSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.sleep_pattern_poly_phasic))) {
                        mSleepPattern = SettingsEntry.SLEEP_PATTERN_POLY_PHASIC; // POLY PHASIC
                    } else {
                        mSleepPattern = SettingsEntry.SLEEP_PATTERN_NORMAL; // NORMAL
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mSleepPattern = SettingsEntry.SLEEP_PATTERN_NORMAL; // NORMAL
            }
        });
        mSexSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.sex_female))) {
                        mSex = SettingsEntry.SEX_FEMALE; // FEMALE
                    } else {
                        mSex= SettingsEntry.SEX_MALE; // MALE
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mSex= SettingsEntry.SEX_MALE; // MALE
            }
        });
    }

    private void insertSettings(){

        ContentValues values = new ContentValues();
        values.put(SettingsEntry.COLUMN_SETTINGS_BLOOD_TYPE, SettingsEntry.BLOOD_TYPE_AB);
        values.put(SettingsEntry.COLUMN_SETTINGS_GOALS, SettingsEntry.GOALS_DIET);
        values.put(SettingsEntry.COLUMN_SETTINGS_SLEEP_PATTERN, SettingsEntry.SLEEP_PATTERN_POLY_PHASIC);
        values.put(SettingsEntry.COLUMN_SETTINGS_HEIGHT, 170);
        values.put(SettingsEntry.COLUMN_SETTINGS_WEIGHT, 47);
        values.put(SettingsEntry.COLUMN_SETTINGS_AGE, 19);
        values.put(SettingsEntry.COLUMN_SETTINGS_SEX, SettingsEntry.SEX_FEMALE);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>> "+ getContentResolver().insert(SettingsEntry.CONTENT_URI, values));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
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
                insertSettings();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                SettingsEntry._ID,
                SettingsEntry.COLUMN_SETTINGS_BLOOD_TYPE,
                SettingsEntry.COLUMN_SETTINGS_GOALS,
                SettingsEntry.COLUMN_SETTINGS_SLEEP_PATTERN,
                SettingsEntry.COLUMN_SETTINGS_HEIGHT,
                SettingsEntry.COLUMN_SETTINGS_WEIGHT,
                SettingsEntry.COLUMN_SETTINGS_AGE,
                SettingsEntry.COLUMN_SETTINGS_SEX,
        };

        return new android.support.v4.content.CursorLoader(
                this,
                mCurrentPetUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data.moveToFirst()){
            mBloodTypeSpinner.setSelection(data.getInt(data.getColumnIndex(SettingsEntry.COLUMN_SETTINGS_BLOOD_TYPE)));
            mGoalsSpinner.setSelection(data.getInt(data.getColumnIndex(SettingsEntry.COLUMN_SETTINGS_GOALS)));
            mSleepPatternSpinner.setSelection(data.getInt(data.getColumnIndex(SettingsEntry.COLUMN_SETTINGS_SLEEP_PATTERN)));
            mHeightEditText.setText(data.getString(data.getColumnIndex(SettingsEntry.COLUMN_SETTINGS_HEIGHT)));
            mWeightEditText.setText(data.getString(data.getColumnIndex(SettingsEntry.COLUMN_SETTINGS_WEIGHT)));
            mAgeEditText.setText(data.getString(data.getColumnIndex(SettingsEntry.COLUMN_SETTINGS_AGE)));
            mSexSpinner.setSelection(data.getInt(data.getColumnIndex(SettingsEntry.COLUMN_SETTINGS_SEX)));
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mBloodTypeSpinner.setSelection(0);
        mGoalsSpinner.setSelection(0);
        mSleepPatternSpinner.setSelection(0);
        mHeightEditText.setText("");
        mWeightEditText.setText("");
        mAgeEditText.setText("");
        mSexSpinner.setSelection(0);
    }
}
