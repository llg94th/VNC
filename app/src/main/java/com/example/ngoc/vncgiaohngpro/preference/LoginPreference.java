package com.example.ngoc.vncgiaohngpro.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ngoc.vncgiaohngpro.objects.Account;

/**
 * Created by phimau on 8/4/2016.
 */
public class LoginPreference {
    private SharedPreferences preferences;
    private static String TAG_ID = "id";
    private static String TAG_PASSWORD = "password";
    private static String TAG_LOGIN = "login";
    private static String TAG_FIRSTLOGIN="isfirst";
    public LoginPreference(Context context) {
        this.preferences = context.getSharedPreferences("logindata",0);
    }
    public void setLogin(Account accountLogined){
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.putString(TAG_ID,accountLogined.getId());
        editor.putBoolean(TAG_FIRSTLOGIN,false);
        editor.putBoolean(TAG_LOGIN,true);
        editor.putString(TAG_PASSWORD,accountLogined.getPassword());
        editor.commit();
    }
    public void setLogout(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }
    public Account getAccLogined(){
        String id=preferences.getString(TAG_ID,"");
        String password =preferences.getString(TAG_PASSWORD,"");
        Account account = new Account(id,password);
        return account;
    }
    public boolean isLogined(){
      return preferences.getBoolean(TAG_LOGIN,false);
    }
    public boolean isFirstLogined(){
        return preferences.getBoolean(TAG_FIRSTLOGIN,true);
    }
}
