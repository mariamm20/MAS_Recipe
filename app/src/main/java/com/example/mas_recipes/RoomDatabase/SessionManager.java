//package com.example.mas_recipes.RoomDatabase;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//
//import com.example.mas_recipes.Registration.LoginActivity;
//
//import java.util.HashMap;
//
//public class SessionManager {
//    SharedPreferences pref;
//    SharedPreferences.Editor editor;
//    Context _context;
//    int PRIVATE_MODE = 0;
//
//    private static final String PREF_NAME = "AndroidHivePref";
//    private static final String IS_LOGIN = "IsLoggedIn";
//    public static final String KEY_userid = "userid";
//
//
//    // Constructor
//    public SessionManager(Context context){
//        this._context = context;
//        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
//        editor = pref.edit();
//    }
//
//    public void createLoginSession(String userid, String email){
//        editor.putBoolean(IS_LOGIN, true);
//        editor.putString(KEY_userid, userid);
//        editor.commit();
//    }
//
//    public void checkLogin(){
//        // Check login status
//        if(!this.isLoggedIn()){
//            Intent i = new Intent(_context, LoginActivity.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            _context.startActivity(i);
//        }
//
//    }
//
//
//    public HashMap<String, String> getUserDetails(){
//        HashMap<String, String> user = new HashMap<String, String>();
//        // user name
//        user.put(KEY_userid, pref.getString(KEY_userid, null));
//
//        // return user
//        return user;
//    }
//
//    public void logoutUser(){
//        // Clearing all data from Shared Preferences
//        editor.clear();
//        editor.commit();
//
//        Intent i = new Intent(_context, LoginActivity.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        _context.startActivity(i);
//
//    }
//
//    public boolean isLoggedIn(){
//        return pref.getBoolean(IS_LOGIN, false);
//    }
//
//
//
////    static final String ID = "id";
////
////    static SharedPreferences getSharedPreferences(Context context) {
////        return PreferenceManager.getDefaultSharedPreferences(context);
////    }
////
////    public static void setId(Context context, int value) {
////        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
////        editor.putInt(ID, value);
////        editor.commit();
////    }
////
////    public static String getId(Context context) {
////        return getSharedPreferences(context).getString(ID, "");
////    }
//
//}