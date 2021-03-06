package com.etibis.notetakinapp;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.preference.PreferenceFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class AppPreferences extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: Check the app or support fragment need to be used

        setContentView(R.layout.activity_note_detail);

        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SettingsFragment settingsFragment = new SettingsFragment();

        fragmentTransaction.add(android.R.id.content, settingsFragment, "SETTINGS_FRAGMENT");

        fragmentTransaction.commit();
    }

    public static class SettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.app_preferences);

        }
    }
}
