package com.example.vjtiapp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class PrefManager {
    Context context;

    SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "ProfileDetails";
    public static final String KEY_NAME = "Name";
    public static final String KEY_BRANCH = "Branch";
    public static final String KEY_YEAR = "Year";

    PrefManager(Context context) {
        this.context = context;
    }

    public void saveLoginDetails(String email, String password) {
        sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Email", email);
        editor.putString("Password", password);
        editor.commit();
    }


    public boolean isUserLogedOut() {
        sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        boolean isEmailEmpty = sharedPreferences.getString("Email", "").isEmpty();
        boolean isPasswordEmpty = sharedPreferences.getString("Password", "").isEmpty();
        return (isEmailEmpty || isPasswordEmpty);
    }

    public void deleteSharedPreferences(){
        sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }

    public void saveProfileDetails(String name,String branch,String year){
        sharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAME,name);
        editor.putString(KEY_BRANCH,branch);
        editor.putString(KEY_YEAR,year);
        editor.commit();
    }

    public String getName(){
        sharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NAME,null);
    }

    public String getBranch(){
        sharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_BRANCH,null);
    }

    public String getYear(){
        sharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_YEAR,null);
    }
}


