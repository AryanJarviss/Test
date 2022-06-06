package com.example.globalmarket.Database;

import android.database.sqlite.SQLiteDatabase;


/**
 * Created by Anil on 12/3/2021.
 */

public class UserModel {
    public static final String TABLE_NAME = "GlobalSuperMarketModel";
    //    All Key
    public static final String KEY_ID = "_id";
    public static final String KEY_USER_ID = "user_id";
    public static final String KeyUserName = "userName";
    public static final String KeyUserEmail = "userEmail";
    public static final String KeyUserMobile = "userPhone";
    public static final String token = "token";
    public static final String KEY_USER_Profile_PIC = "Profile_pic";
    public static final String KEY_USER_user_type = "user_type";
    public static final String KEY_USER_country_id = "country_id";
    public static final String KEY_USER_state_id = "state_id";
    public static final String KEY_USER_city_id = "city_id";
    public static final String KEY_USER_address = "address";
    public static final String KEY_USER_latitude = "latitude";
    public static final String KEY_USER_longitude = "longitude";
    public static final String KEY_USER_country_name = "country_name";
    public static final String KEY_USER_state_name = "state_name";
    public static final String KEY_USER_city_name = "cityName";
    public static final String KEY_USER_bearing = "bearing";
    public static final String KEY_USER_device_type = "deviceType";
    public static final String KEY_USER_fcm_id = "fcm_id";
    //   Model Key
    public String UserID;
    public String UserName;
    public String UserEmail;
    public String UserMobile;
    public String UserProfile_PIC;
    public String UserToken;
    public String user_type;
    public String country_id;
    public String state_id;
    public String city_id;
    public String address;
    public String latitude;
    public String longitude;
    public String country_name;
    public String state_name;
    public String fcm_id;
    public String cityName;
    public String bearing;
    public String deviceType;

    /**
     * Create Table Qurey
     * @param db
     */
    public static void CreateTable(SQLiteDatabase db) {
        String CreateTableQuery = "create table " + TABLE_NAME + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_USER_ID + " text," +
                token + " text," +
                KeyUserName + " text," +
                KeyUserEmail + " text," +
                KeyUserMobile + " text, " +
                KEY_USER_user_type + " text, " +
                KEY_USER_country_id + " text, " +
                KEY_USER_state_id + " text, " +
                KEY_USER_city_id + " text, " +
                KEY_USER_address + " text, " +
                KEY_USER_latitude + " text, " +
                KEY_USER_longitude + " text, " +
                KEY_USER_country_name + " text, " +
                KEY_USER_state_name + " text, " +
                KEY_USER_city_name + " text, " +
                KEY_USER_bearing + " text, " +
                KEY_USER_device_type + " text, " +
                KEY_USER_fcm_id + " text, " +
                KEY_USER_Profile_PIC + " text " +
                ")";

        db.execSQL(CreateTableQuery);
    }

    /**
     * @param db
     */
    public static void dropTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

}
