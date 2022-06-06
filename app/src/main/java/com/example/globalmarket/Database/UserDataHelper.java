package com.example.globalmarket.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.globalmarket.UtilityTools.Utils;

import java.util.ArrayList;


/**
 * Created by Anil on 12/3/2021.
 */

public class UserDataHelper {
    private static UserDataHelper instance;
    Context cx;
    private SQLiteDatabase db;
    private DataManager dm;

    /**
     * UserDataHelper constructor
     *
     * @param cx
     */
    public UserDataHelper(Context cx) {
        instance = this;
        this.cx = cx;
        dm = new DataManager(cx, DataManager.DATABASE_NAME, null, DataManager.DATABASE_VERSION);
    }

    /**
     * UserDataHelper instance
     *
     * @return
     */
    public static UserDataHelper getInstance() {
        return instance;
    }

    /**
     * open db
     */
    public void open() {
        db = dm.getWritableDatabase();
    }

    /**
     * close db
     */
    public void close() {
        //  db.close();
    }

    /**
     * read db
     */
    public void read() {
        db = dm.getReadableDatabase();
    }

    /**
     * delete by user id from the table
     *
     * @param userModel
     */
    public void delete(UserModel userModel) {
        open();
        db.delete(UserModel.TABLE_NAME, UserModel.KEY_USER_ID + " = '"
                + userModel.UserID + "'", null);
        close();
    }

    /**
     * delete All Data From the Table
     */
    public void deleteAll() {
        open();
        db.delete(UserModel.TABLE_NAME, null, null);
        close();
    }

    /**
     * is Exist in table
     *
     * @param userModel
     * @return
     */
    private boolean isExist(UserModel userModel) {
        read();
        Cursor cur = db.rawQuery("select * from " + UserModel.TABLE_NAME + " where " + UserModel.KEY_USER_ID + "='"
                + userModel.UserID + "'", null);
        if (cur.moveToFirst()) {
            return true;
        }
        return false;
    }

    /**
     * insert Data in table
     *
     * @param userModel
     */
    public void insertData(UserModel userModel) {
        open();
        ContentValues values = new ContentValues();
        values.put(UserModel.KEY_USER_ID, userModel.UserID);
        values.put(UserModel.KeyUserName, userModel.UserName);
        values.put(UserModel.KeyUserEmail, userModel.UserEmail);
        values.put(UserModel.KeyUserMobile, userModel.UserMobile);
        values.put(UserModel.KEY_USER_Profile_PIC, userModel.UserProfile_PIC);
        values.put(UserModel.token, userModel.UserToken);
        values.put(UserModel.KEY_USER_user_type, userModel.user_type);
        values.put(UserModel.KEY_USER_country_id, userModel.country_id);
        values.put(UserModel.KEY_USER_state_id, userModel.state_id);
        values.put(UserModel.KEY_USER_city_id, userModel.city_id);
        values.put(UserModel.KEY_USER_address, userModel.address);
        values.put(UserModel.KEY_USER_latitude, userModel.latitude);
        values.put(UserModel.KEY_USER_longitude, userModel.longitude);
        values.put(UserModel.KEY_USER_country_name, userModel.country_name);
        values.put(UserModel.KEY_USER_state_name, userModel.state_name);
        values.put(UserModel.KEY_USER_fcm_id, userModel.fcm_id);
        values.put(UserModel.KEY_USER_city_name, userModel.cityName);
        values.put(UserModel.KEY_USER_bearing, userModel.bearing);
        values.put(UserModel.KEY_USER_device_type, userModel.deviceType);

        if (!isExist(userModel)) {
            Utils.E("insert successfully");
            Utils.E("Values::"+values);
            db.insert(UserModel.TABLE_NAME, null, values);
        } else {
            Utils.E("update successfully");
            db.update(UserModel.TABLE_NAME, values, UserModel.KEY_USER_ID + "=" + userModel.UserID, null);
        }
        close();
    }


    /**
     * Return User Array List
     *
     * @return
     */
    @SuppressLint("Range")
    public ArrayList<UserModel> getList() {
        ArrayList<UserModel> userItem = new ArrayList<UserModel>();
        read();
        Cursor cursor = db.rawQuery("select * from " + UserModel.TABLE_NAME, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToLast();
            do {
                UserModel taxiModel = new UserModel();
                taxiModel.UserID = cursor.getString(cursor.getColumnIndex(UserModel.KEY_USER_ID));
                taxiModel.UserName = cursor.getString(cursor.getColumnIndex(UserModel.KeyUserName));
                taxiModel.UserEmail = cursor.getString(cursor.getColumnIndex(UserModel.KeyUserEmail));
                taxiModel.UserMobile = cursor.getString(cursor.getColumnIndex(UserModel.KeyUserMobile));
                taxiModel.UserProfile_PIC = cursor.getString(cursor.getColumnIndex(UserModel.KEY_USER_Profile_PIC));
                taxiModel.UserToken = cursor.getString(cursor.getColumnIndex(UserModel.token));
                taxiModel.user_type = cursor.getString(cursor.getColumnIndex(UserModel.KEY_USER_user_type));
                taxiModel.country_id = cursor.getString(cursor.getColumnIndex(UserModel.KEY_USER_country_id));
                taxiModel.state_id = cursor.getString(cursor.getColumnIndex(UserModel.KEY_USER_state_id));
                taxiModel.city_id = cursor.getString(cursor.getColumnIndex(UserModel.KEY_USER_city_id));
                taxiModel.address = cursor.getString(cursor.getColumnIndex(UserModel.KEY_USER_address));
                taxiModel.latitude = cursor.getString(cursor.getColumnIndex(UserModel.KEY_USER_latitude));
                taxiModel.longitude = cursor.getString(cursor.getColumnIndex(UserModel.KEY_USER_longitude));
                taxiModel.country_name = cursor.getString(cursor.getColumnIndex(UserModel.KEY_USER_country_name));
                taxiModel.state_name = cursor.getString(cursor.getColumnIndex(UserModel.KEY_USER_state_name));
                taxiModel.fcm_id = cursor.getString(cursor.getColumnIndex(UserModel.KEY_USER_fcm_id));
                taxiModel.cityName = cursor.getString(cursor.getColumnIndex(UserModel.KEY_USER_city_name));
                taxiModel.bearing = cursor.getString(cursor.getColumnIndex(UserModel.KEY_USER_bearing));
                taxiModel.deviceType = cursor.getString(cursor.getColumnIndex(UserModel.KEY_USER_device_type));
                userItem.add(taxiModel);

            } while ((cursor.moveToPrevious()));
            cursor.close();
        }
        close();
        return userItem;
    }


}