package com.example.admin.movies;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by admin on 01-01-2017.
 */

public class SettingsFragment extends PreferenceFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String list_key = "sort_by";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

    }
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

        if(s.equals(list_key)){
            Preference preference = findPreference(list_key);
            preference.setSummary(((ListPreference) preference).getEntry());
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("activity","settings resume");
        PreferenceManager.getDefaultSharedPreferences(getActivity()).
                registerOnSharedPreferenceChangeListener(this);

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("activity","settings pause");
        PreferenceManager.getDefaultSharedPreferences(getActivity()).
                unregisterOnSharedPreferenceChangeListener(this);

    }
}
