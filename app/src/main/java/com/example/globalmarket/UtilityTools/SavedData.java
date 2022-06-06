package com.example.globalmarket.UtilityTools;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Anil on 12/3/2021.
 */
public class SavedData {

    private static final String location = "location";
    private static final String Lat = "Lat";
    private static final String Long = "Long";
    private static final String OtpVerify = "OtpVerify";
    private static final String Welcome= "Welcome";
    private static final String FirebaseToken = "FirebaseToken";
    private static final String NotificationOnOff = "NotificationOnOff";
    private static final String Status = "Status";
    private static final String Language = "Language";
    private static final String AskAgain = "AskAgain";
    private static final String UserAddress = "UserAddress";
    private static final String AddressId = "AddressId";
    private static SharedPreferences prefs;

    public static SharedPreferences getInstance() {
        if (prefs == null) {
            prefs = PreferenceManager.getDefaultSharedPreferences(AppController.getInstance());
        }
        return prefs;
    }
    public static boolean getAskAgain() {
        return getInstance().getBoolean(AskAgain, false);
    }

    public static void saveAskAgain(boolean role) {
        SharedPreferences.Editor editor = getInstance().edit();
        editor.putBoolean(AskAgain, role);
        editor.apply();
    }

    public static String getLocation() {
        return getInstance().getString(location, Constants.blank);
    }

    public static void saveLocation(String role) {
        SharedPreferences.Editor editor = getInstance().edit();
        editor.putString(location, role);
        editor.apply();
    }

    public static String getLanguage() {
        return getInstance().getString(Language, Constants.english);
    }

    public static void saveLanguage(String role) {
        SharedPreferences.Editor editor = getInstance().edit();
        editor.putString(Language, role);
        editor.apply();
    }

    public static String getStatus() {
        return getInstance().getString(Status, Constants.blank);
    }

    public static void saveStatus(String role) {
        SharedPreferences.Editor editor = getInstance().edit();
        editor.putString(Status, role);
        editor.apply();
    }

    public static String getLat() {
        return getInstance().getString(Lat, Constants.blank);
    }

    public static void saveLat(String role) {
        SharedPreferences.Editor editor = getInstance().edit();
        editor.putString(Lat, role);
        editor.apply();
    }

    public static String getLong() {
        return getInstance().getString(Long, Constants.blank);
    }

    public static void saveLong(String role) {
        SharedPreferences.Editor editor = getInstance().edit();
        editor.putString(Long, role);
        editor.apply();
    }

    public static boolean getOtpVerify() {
        return getInstance().getBoolean(OtpVerify, false);
    }

    public static void saveOtpVerify(boolean role) {
        SharedPreferences.Editor editor = getInstance().edit();
        editor.putBoolean(OtpVerify, role);
        editor.apply();
    }

    public static boolean getWelcome() {
        return getInstance().getBoolean(Welcome, false);
    }

    public static void saveWelcome(boolean role) {
        SharedPreferences.Editor editor = getInstance().edit();
        editor.putBoolean(Welcome, role);
        editor.apply();
    }

    public static String getNotificationOnOff() {
        return getInstance().getString(NotificationOnOff, Constants.On);
    }

    public static void saveNotificationOnOff(String isNotification) {
        SharedPreferences.Editor editor = getInstance().edit();
        editor.putString(NotificationOnOff, isNotification);
        editor.apply();
    }


    public static String getFirebaseToken() {
        return getInstance().getString(FirebaseToken, Constants.blank);
    }

    public static void saveFirebaseToken(String token) {
        SharedPreferences.Editor editor = getInstance().edit();
        editor.putString(FirebaseToken, token);
        editor.apply();
    }


    public static String getUserAddress() {
        return getInstance().getString(UserAddress, Constants.blank);
    }

    public static void saveUserAddress(String address) {
        SharedPreferences.Editor editor = getInstance().edit();
        editor.putString(UserAddress, address);
        editor.apply();
    }

    public static String getAddressId() {
        return getInstance().getString(AddressId, Constants.blank);
    }

    public static void saveAddressId(String addressId) {
        SharedPreferences.Editor editor = getInstance().edit();
        editor.putString(AddressId, addressId);
        editor.apply();
    }



}