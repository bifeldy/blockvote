package id.bifeldy.blockvote.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import id.bifeldy.blockvote.R;

public class LangApp extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {

        // Get language Shared Preference
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        String languageCode = sharedPreferences.getString(
            newBase.getResources().getString(R.string.pref_language_list_key),
            Utility.getSystemLocale()
        );

        assert languageCode != null;
        super.attachBaseContext(Utility.changeLanguage(newBase, languageCode.split("-")[0]));
    }

}
