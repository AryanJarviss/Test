package com.example.globalmarket.Retrofit;

import com.example.globalmarket.Model.AllResponseModel;
import com.example.globalmarket.UtilityTools.Const;
import com.example.globalmarket.UtilityTools.Constants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST(Const.user_signup)
    Call<AllResponseModel> SignUp(@Field(Constants.mobile) String mobile);

/*    @GET(Const.getUserCountUnreadNotification)
    Call<AllResponseModel> getNotificationCount(@Header(Constants.Authorization) String token);*/

}
