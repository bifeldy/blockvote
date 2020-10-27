package id.bifeldy.blockvote.helpers;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import androidx.core.os.ConfigurationCompat;

import java.util.Locale;

public class Utility {

    // https://stackoverflow.com/questions/39705739/android-n-change-language-programmatically
    static ContextWrapper changeLanguage(Context context, String lang_code) {
        Resources rs = context.getResources();
        Configuration config = rs.getConfiguration();
        Locale locale = new Locale(lang_code);
        Locale.setDefault(locale);
        config.setLocale(locale);
        return new ContextWrapper(context.createConfigurationContext(config));
    }

    public static String getSystemLocale() {
        return ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).toLanguageTags();
    }

    public static int calcNumOfCols(Context context, float colWidthDp) {

        // For example column Width dp = 180
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;

        // +0.5 for correct rounding to int.
        return (int) (screenWidthDp / colWidthDp + 0.5);
    }

}
