package com.kerolossalib.kirfood;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesSettings {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static String PACKAGE_NAME = "com.kerolossalib.kirfood";


    public static void setSharedPreferences(Context context, String key, String value) {
        editor = context.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void setSharedPreferences(Context context, String key, boolean value) {
        editor = context.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void setSharedPreferences(Context context, String key, int value) {
        editor = context.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static String getStringFromPreferences(Context context, String key) {
        sharedPreferences = context.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }

    public static boolean getBooleanFromPreferences(Context context, String key) {
        sharedPreferences = context.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    public static void deleteValueFromPreferences(Context context, String key) {
        editor = context.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE).edit();
        editor.remove(key);
        editor.apply();
    }

    public static boolean isContainedInPreferences(Context context, String key) {
        sharedPreferences = context.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.contains(key);
    }


}
