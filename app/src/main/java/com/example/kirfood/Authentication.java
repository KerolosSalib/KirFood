package com.example.kirfood;

import android.util.Patterns;
public class Authentication {

    private static final int MIN_PASSWORD_LENGTH = 6;
    private static boolean PASSWORD_VALID;
    private static boolean EMAIL_VALID;
    private static boolean PHONE_NUMBER_VALID;


    public static void validateEmail(String email){
        EMAIL_VALID = Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static void validatePassword(String password){
        PASSWORD_VALID =  password.length() > MIN_PASSWORD_LENGTH;
    }

    public static  void validatePhoneNumber(String phoneNumber){
        PASSWORD_VALID = Patterns.PHONE.matcher(phoneNumber).matches();
    }

    public static boolean canRegister(){
        return EMAIL_VALID && PASSWORD_VALID && PHONE_NUMBER_VALID;
    }

}
