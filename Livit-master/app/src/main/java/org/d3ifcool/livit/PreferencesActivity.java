package org.d3ifcool.livit;

import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
    }
    public static class LivitPreferenceFragment extends PreferenceFragment
            implements Preference.OnPreferenceChangeListener{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings);

            Preference bloodType = findPreference(getString(R.string.settings_blood_type_key));
            bindPreferenceSummaryToValue(bloodType);

            Preference goals = findPreference(getString(R.string.settings_goals_key));
            bindPreferenceSummaryToValue(goals);

            Preference sleepPattern = findPreference(getString(R.string.settings_sleep_pattern_key));
            bindPreferenceSummaryToValue(sleepPattern);

            Preference sex = findPreference(getString(R.string.settings_sex_key));
            bindPreferenceSummaryToValue(sex);

            Preference height = findPreference(getString(R.string.settings_height_key));
            bindPreferenceSummaryToValue(height);

            Preference weight = findPreference(getString(R.string.settings_weight_key));
            bindPreferenceSummaryToValue(weight);

            Preference age = findPreference(getString(R.string.settings_age_key));
            bindPreferenceSummaryToValue(age);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object o) {
            String stringValue = o.toString();
            if (preference instanceof ListPreference){
                //settings order by
                ListPreference listPreference = (ListPreference) preference;
                int prefIndex = listPreference.findIndexOfValue(stringValue);
                if (prefIndex>= 0) {
                    CharSequence[] labels = listPreference.getEntries();
                    preference.setSummary(labels[prefIndex]);
                }
            }else{
                //setting minimum magnitude
                preference.setSummary(stringValue);
            }
            return true;
        }

        private void bindPreferenceSummaryToValue(Preference preference){
            preference.setOnPreferenceChangeListener(this);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            String prefString = sharedPreferences.getString(preference.getKey(),"");
            onPreferenceChange(preference, prefString);
        }
    }
}
