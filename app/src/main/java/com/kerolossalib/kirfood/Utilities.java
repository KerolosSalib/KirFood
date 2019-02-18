package com.kerolossalib.kirfood;

import android.content.Context;
import android.util.Patterns;
import android.widget.Toast;

public class Utilities {

    private static final int MIN_PASSWORD_LENGTH = 6;
    public static boolean EMAIL_VALID;
    public static boolean PASSWORD_VALID;
    public static boolean PHONE_NUMBER_VALID;


    public static boolean validateEmail(String email){
        EMAIL_VALID = Patterns.EMAIL_ADDRESS.matcher(email).matches();
        return EMAIL_VALID;
    }

    public static boolean validatePassword(String password){
        PASSWORD_VALID =  password.length() > MIN_PASSWORD_LENGTH;
        return PASSWORD_VALID;
    }

    public static  boolean validatePhoneNumber(String phoneNumber){
        PHONE_NUMBER_VALID = Patterns.PHONE.matcher(phoneNumber).matches();
        return PHONE_NUMBER_VALID;
    }

    public static boolean canRegister(){
        return EMAIL_VALID && PASSWORD_VALID && PHONE_NUMBER_VALID;
    }

    public static boolean canLogin(){
        return  EMAIL_VALID && PASSWORD_VALID;
    }

    public static void showToast(Context c, String t){
        Toast.makeText(c, t, Toast.LENGTH_LONG).show();
    }

}
