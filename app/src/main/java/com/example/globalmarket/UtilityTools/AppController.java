package com.example.globalmarket.UtilityTools;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.example.globalmarket.Database.UserDataHelper;


/**
 * Created by Anil on 12/3/2021.
 */


public class AppController extends MultiDexApplication {


    //   private RequestQueue mRequestQueue;
    public static final String TAG = AppController.class.getSimpleName();
    public static final int GRANTED = 0;
    public static final int DENIED = 1;
    public static final int BLOCKED = 2;
    public static int invoiceId = 0;
    static Context context;
    private static AppController mInstance;
    ConnectivityManager connectivityManager;
    NetworkInfo wifiInfo, mobileInfo;
    boolean connected = false;

    /**
     * @return
     */
    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public AppController() {
        mInstance = this;
    }
    public static Context getContext() {
        return mInstance;
    }

    /**
     * @param ctx
     * @return
     */
    public static AppController getInstance(Context ctx) {
        context = ctx.getApplicationContext();
        return mInstance;
    }

    /**
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, LocaleHelper.getLanguage(base)));
        // super.attachBaseContext(base);
//        MultiDex.install(this);
    }

    /**
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleHelper.setLocale(context, LocaleHelper.getLanguage(context));
    }

    /**
     * @return
     */
    public boolean isOnline() {
        try {
            connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isAvailable() &&
                    networkInfo.isConnected();
            return connected;
        } catch (Exception e) {
            System.out.println("CheckConnectivity Exception: " + e.getMessage());
            Log.v("connectivity", e.toString());
        }
        return connected;
    }


    @Override
    public void onCreate() {
        MultiDex.install(this);
        super.onCreate();
        mInstance = this;
        new UserDataHelper(this);
        SavedData.getInstance();
    }


}

