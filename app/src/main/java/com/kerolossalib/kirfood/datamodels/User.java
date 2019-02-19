package com.kerolossalib.kirfood.datamodels;

import android.content.Context;

import com.kerolossalib.kirfood.SharedPreferencesSettings;

public class User {

    public static void login(Context context, String jwt) {
        SharedPreferencesSettings.setSharedPreferences(context, "jwt", jwt);
    }

    public static void logout(Context context) {
        SharedPreferencesSettings.deleteValueFromPreferences(context, "jwt");
    }

    public static boolean isLoggedin(Context context) {
       return SharedPreferencesSettings.isContainedInPreferences(context, "jwt");
    }
}
