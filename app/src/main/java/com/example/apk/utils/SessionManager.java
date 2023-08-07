package com.example.apk.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.apk.model.DataLogin;

import java.util.HashMap;

public class SessionManager {

    private Context _context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String NIK = "nik";
    public static final String USER = "user";
    public static final String NAMA_LENGKAP = "nama_lengkap";
    public static final String STATUS = "status";

    public static final String LV = "lv";


    public SessionManager(Context context){
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(DataLogin user){

        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(NIK, user.getNik());
        editor.putString(USER, user.getUser());
        editor.putString(NAMA_LENGKAP, user.getNama_lengkap());
        editor.putString(STATUS, user.getStatus());
        editor.putString(LV, user.getLv());
        editor.commit();
    }

    public HashMap<String,String> getUserDetail(){
        HashMap<String,String> user = new HashMap<>();
        user.put(NIK, sharedPreferences.getString(NIK,null));
        user.put(USER, sharedPreferences.getString(USER,null));
        user.put(NAMA_LENGKAP, sharedPreferences.getString(NAMA_LENGKAP,null));
        user.put(STATUS, sharedPreferences.getString(STATUS,null));
        user.put(LV, sharedPreferences.getString(LV,null));
        return user;

    }
    public void logoutSession(){
        editor.clear();
        editor.commit();
        
    }
    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }
    public String getNik()
    {
        return sharedPreferences.getString(NIK, null);
    }

    public String getUser()
    {
        return sharedPreferences.getString(USER, null);
    }
    public String getNamaLengkap()
    {
        return sharedPreferences.getString(NAMA_LENGKAP, null);
    }
    public String getStatus()
    {
        return sharedPreferences.getString(STATUS, null);
    }
    public String getLv()
    {
        return sharedPreferences.getString(LV, null);
    }



}
