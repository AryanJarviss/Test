package com.example.globalmarket.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AllResponseModel {

@SerializedName("message")
@Expose
public String message;
@SerializedName("key")
@Expose
public String key;
@SerializedName("OTP")
@Expose
public String otp;

}