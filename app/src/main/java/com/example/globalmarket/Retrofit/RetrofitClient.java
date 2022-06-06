package com.example.globalmarket.Retrofit;


import com.example.globalmarket.UtilityTools.AppController;
import com.example.globalmarket.UtilityTools.Const;
import com.example.globalmarket.UtilityTools.Constants;
import com.example.globalmarket.UtilityTools.Utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created By Anil
 */
public class RetrofitClient {

//    private static Retrofit retrofit = null;
//    public static ApiService getClient() {
//        Context context;
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .connectTimeout(2, TimeUnit.MINUTES)
//                .readTimeout(2, TimeUnit.MINUTES).build();
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(Const.HOST_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(client)
//                    .build();
//        }
//        return retrofit.create(ApiService.class);
//    }



    private static Retrofit retrofit = null;

    public static ApiService getClient() {

    /*    if(!AppController.getInstance().isOnline()){
            Utils.E("enetr");
            Utils.T_Long(AppController.getContext(),"Please check your Internet Connection");
        }*/
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES);

        if (Const.Development.equals(Constants.Debug)) {
            client.addInterceptor(interceptor);
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(Const.HOST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
        return retrofit.create(ApiService.class);
    }

}
