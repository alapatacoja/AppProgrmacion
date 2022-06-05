package com.example.taserfan.preferencias;

import android.os.Bundle;

import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.taserfan.API.API;
import com.example.taserfan.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        //Temas
        ListPreference temas = getPreferenceManager().findPreference("temassettings");
        if (temas.getValue() == null)
            temas.setValue(SetupTema.Mode.DEFAULT.name());

        temas.setOnPreferenceChangeListener(((preference, newValue) -> {
            SetupTema.applyTheme(SetupTema.Mode.valueOf((String) newValue));
            return true;
        }));

        //EditTexts
        final EditTextPreference ip = findPreference("edit_text_preference_1");
        ip.setSummary(GestionPreferencias.getInstance().getEditTextPreference(getContext()));
        ip.setOnPreferenceChangeListener((preference, newValue) -> {
            ip.setSummary(""+newValue);
            API.Routes.IP= (String) newValue;
            return true;
        });

        final EditTextPreference port = findPreference("edit_text_preference_port");
        port.setSummary(GestionPreferencias.getInstance().getEditTextPreference(getContext()));
        port.setOnPreferenceChangeListener((preference, newValue) -> {
            port.setSummary(""+newValue);
            API.Routes.PUERTO= (String) newValue;
            return true;
        });
    }
}