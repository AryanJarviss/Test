package com.example.globalmarket.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.globalmarket.Model.AllResponseModel;
import com.example.globalmarket.R;
import com.example.globalmarket.Retrofit.ApiService;
import com.example.globalmarket.Retrofit.RetrofitClient;
import com.example.globalmarket.UtilityTools.UserAccount;
import com.example.globalmarket.UtilityTools.Utils;
import com.example.globalmarket.databinding.ActivityRegistrationBinding;
import com.example.globalmarket.databinding.BottomsheetLayoutBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    Activity activity = RegistrationActivity.this;
    ActivityRegistrationBinding activityRegistrationBinding;
    BottomSheetDialog bottomSheetDialog;
    BottomsheetLayoutBinding bottomBinding;
    ApiService apiService;
    String Otp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegistrationBinding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(activityRegistrationBinding.getRoot());

        iniitalise();
    }

    private void iniitalise() {

        apiService= RetrofitClient.getClient();
        activityRegistrationBinding.GetOtp.setOnClickListener(this);
        activityRegistrationBinding.procdaas.setOnClickListener(this);
        activityRegistrationBinding.cvhomezsss.setOnClickListener(this);
    }

    private  void getBottomSheet(String OTP){
        bottomSheetDialog = new BottomSheetDialog(RegistrationActivity.this, R.style.Bottomsheetstyle);
        bottomBinding = BottomsheetLayoutBinding.inflate(getLayoutInflater());
        bottomSheetDialog.setContentView(bottomBinding.getRoot());
        bottomSheetDialog.show();
        bottomBinding.cvcontinuessspp.setOnClickListener(this);
        bottomBinding.procdaasss.setOnClickListener(this);
        Utils.T_Long(getBaseContext(),"OTP ::"+OTP);
    }

    private void getOtpTask(String mobile){
        Dialog progressdialog=Utils.initProgressDialog(activity);
        apiService.SignUp(mobile).enqueue(new Callback<AllResponseModel>() {
            @Override
            public void onResponse(Call<AllResponseModel> call, Response<AllResponseModel> response) {
                if(response.isSuccessful()){
                    Utils.E("data"+response.body().toString());
                    progressdialog.dismiss();

                    if(response.body()!=null){
                        Toast.makeText(activity, "Otp::" + response.body().otp, Toast.LENGTH_LONG).show();
                        Utils.E("activity "+response.body().otp);
                        Otp=response.body().otp;
                        getBottomSheet(response.body().otp);

                    }else{
                        Utils.T_Long(activity,"Something Went Wrong");
                    }
                }
            }

            @Override
            public void onFailure(Call<AllResponseModel> call, Throwable t) {
                Utils.T(activity, "Error:: " + t.getMessage());
            }
        });

    }

    @Override
    public void onClick(View view) {

        if (view == activityRegistrationBinding.GetOtp) {
            if (UserAccount.isEmpty(activity, activityRegistrationBinding.etname)) {
                if (UserAccount.isEmailValid(activityRegistrationBinding.etemail, activity)) {
                    if (UserAccount.isValidPhoneNumber(activityRegistrationBinding.etMobileNumber, activity)) {
                        getOtpTask(activityRegistrationBinding.etMobileNumber.getText().toString());

                    } else {
                        Utils.T(activity, UserAccount.errorMessage);
                    }
                } else {
                    Utils.T(activity, UserAccount.errorMessage);
                }
            } else {
                Utils.T(activity, UserAccount.errorMessage);
            }

        } else if (view == activityRegistrationBinding.procdaas) {
            Utils.I(activity, WelcomeActivity.class, null);
        } else if (view == activityRegistrationBinding.cvhomezsss) {
            finish();
        } else if (view == bottomBinding.cvcontinuessspp) {

            if(bottomBinding.firstPinView.getText().toString().trim().equals(Otp.trim())){
                Utils.I(activity, HomeActivity.class, null);
                Utils.T(activity, "Verify");
            }else{
                Utils.T(activity,"Incorrect OTP");
            }

        } else if (view == bottomBinding.procdaasss) {
            Utils.T(activity, "Resend  OTP");
        }
    }
}