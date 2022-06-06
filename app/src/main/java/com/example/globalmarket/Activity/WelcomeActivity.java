package com.example.globalmarket.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.globalmarket.Model.AllResponseModel;
import com.example.globalmarket.R;
import com.example.globalmarket.Retrofit.ApiService;
import com.example.globalmarket.Retrofit.RetrofitClient;
import com.example.globalmarket.UtilityTools.UserAccount;
import com.example.globalmarket.UtilityTools.Utils;
import com.example.globalmarket.databinding.ActivityWelcomeBinding;
import com.example.globalmarket.databinding.BottomsheetLayoutBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    Activity activity = WelcomeActivity.this;
    ActivityWelcomeBinding activityWelcomeBinding;
    BottomSheetDialog bottomSheetDialog;
    ApiService apiService;
    String Otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityWelcomeBinding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(activityWelcomeBinding.getRoot());
        initialize();
    }

    private void initialize() {
        apiService = RetrofitClient.getClient();

        activityWelcomeBinding.cvhomezsss.setOnClickListener(this);
        activityWelcomeBinding.cvcontinues.setOnClickListener(this);
        activityWelcomeBinding.procdaa.setOnClickListener(this);


    }

    private void bottomSheetOtpTask(String otp) {
        BottomsheetLayoutBinding bottomSheetBinding = BottomsheetLayoutBinding.inflate(getLayoutInflater());
        bottomSheetDialog = new BottomSheetDialog(WelcomeActivity.this, R.style.Bottomsheetstyle);
        bottomSheetDialog.setContentView(bottomSheetBinding.getRoot());
        bottomSheetDialog.show();
        bottomSheetBinding.cvcontinuessspp.setOnClickListener(view -> {
            if (bottomSheetBinding.firstPinView.getText().toString().trim().equals(Otp.trim())) {
                Utils.I(activity, HomeActivity.class, null);
                Utils.T(activity, "Verify");
            }else{
                Utils.T(activity,"Incorrect OTP");
            }
        });
        Utils.T_Long(getBaseContext(), "Otp:: " + otp);
    }

    private void getOtpTask(String mobile) {
        Dialog progressDialog = Utils.initProgressDialog(activity);
        apiService.SignUp(mobile).enqueue(new Callback<AllResponseModel>() {
            @Override
            public void onResponse(Call<AllResponseModel> call, Response<AllResponseModel> response) {
                if (response.isSuccessful()) {
                    Utils.E("data: " + response.body().toString());
                    progressDialog.dismiss();

                    if (response.body() != null) {
                        Toast.makeText(activity, "Otp::" + response.body().otp, Toast.LENGTH_LONG).show();
                        Utils.E("activity," + response.body().otp);
                        Otp = response.body().otp;
                        bottomSheetOtpTask(response.body().otp);
                    } else {
                        Utils.T_Long(activity, "Something Went Wrong");
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
        if (view == activityWelcomeBinding.cvhomezsss) {
            finish();
        } else if (view == activityWelcomeBinding.cvcontinues) {
            if (UserAccount.isValidPhoneNumber(activityWelcomeBinding.etmobile, activity)) {
                getOtpTask(activityWelcomeBinding.etmobile.getText().toString());
            } else {
                Utils.T(activity, UserAccount.errorMessage);
            }
        } else if (view == activityWelcomeBinding.procdaa) {
            Utils.I(activity, RegistrationActivity.class, null);
        }


    }
}