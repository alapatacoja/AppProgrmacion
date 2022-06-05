package com.example.taserfan.preferencias;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.example.taserfan.API.API;
import com.example.taserfan.R;

public class GestionPreferencias {

    private SharedPreferences preferences;
    private static GestionPreferencias gestionPreferencias;

    private GestionPreferencias(){}

    public static GestionPreferencias getInstance(){
        if (gestionPreferencias == null)
            gestionPreferencias = new GestionPreferencias();
        return gestionPreferencias;
    }

    private void inicializa(Context context) {
        if (preferences == null)
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }


    public String getEditTextPreference(Context context){
        inicializa(context);
        return preferences.getString("editTextPreferenceKey", API.Routes.URL);
    }

    public String getTheme(Context context){
        inicializa(context);
        return preferences.getString(context.getString(R.string.settings_theme_key),SetupTema.Mode.DEFAULT.name());
    }

    public Context getContext(){
        return getContext();
    }
}
