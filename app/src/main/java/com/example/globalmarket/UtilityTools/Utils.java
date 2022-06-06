package com.example.globalmarket.UtilityTools;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.core.content.ContextCompat;


import com.example.globalmarket.Database.UserDataHelper;
import com.example.globalmarket.Database.UserModel;
import com.example.globalmarket.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by Anil on 12/3/2021.
 */

public class Utils {

/*    public static   DialogLoginBinding binding;
    public static   CountDownTimer countDownTimer;
    public static   DialogRefferalBinding dialogRefferalBinding;
    public static  DialogOtpVerificationBinding    verificationBinding;
    public static String Key,mobile,userId;
    public static ApiService apiService = RetrofitClient.getClient();*/

    public static UserModel GetSession() {
        return UserDataHelper.getInstance().getList().get(0);
    }

    public static boolean IS_LOGIN() {
        return UserDataHelper.getInstance().getList().size() > 0;
    }

    public static void I(Context cx, Class<?> startActivity, Bundle data) {
        Intent i = new Intent(cx, startActivity);
        if (data != null)
            i.putExtras(data);
        cx.startActivity(i);
    }

    public static <T> List<T> removeDuplicates(List<T> list) {

        // Create a new ArrayList
        List<T> newList = new ArrayList<T>();

        // Traverse through the first list
        for (T element : list) {

            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }

        // return the new list
        return newList;
    }

/*    public static void UnAuthorizationToken(Context cx) {
        UserDataHelper.getInstance().deleteAll();
        I_clear(cx, DashboardActivity.class, null);
    }*/

    /**
     * Change the status bar Color of the Activity to the Desired Color.
     * @param activity - Activity
     * @param color - Desired Color
     */

    public static void changeStatusBarColor(Activity activity, int color) {
        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(activity, color));
    }

    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
//getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

//Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Utils.E("Package Name=" + context.getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

// String key = new String(Base64.encodeBytes(md.digest()));
                Utils.E("Key Hash=" + key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Utils.E("Name not found" + e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Utils.E("No such an algorithm" + e.toString());
        } catch (Exception e) {
            Utils.E("Exception" + e.toString());
        }

        return key;
    }


    public static void I_finish(Context cx, Class<?> startActivity, Bundle data) {
        Intent i = new Intent(cx, startActivity);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        if (data != null)
            i.putExtras(data);
        cx.startActivity(i);
    }

    public static void I_clear(Context cx, Class<?> startActivity, Bundle data) {
        Intent i = new Intent(cx, startActivity);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        if (data != null)
            i.putExtras(data);
        cx.startActivity(i);
    }

    public static void E(String msg) {
         if (Const.Development.equals(Constants.Debug))
            Log.e("Log.E By Anil", msg);
    }

    public static AlertDialog.Builder alert(Context context,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
       builder.setTitle(R.string.app_name).setMessage(Message)
                .setPositiveButton(R.string.ok, (dialog, which) -> dialog.dismiss()).show();
        return builder;
    }

    public static String getFormattedDate(long smsTimeInMilis, Context context) {
        Calendar smsTime = Calendar.getInstance();
        smsTime.setTimeInMillis(smsTimeInMilis);

        Calendar now = Calendar.getInstance();

        final String timeFormatString = "h:mm aa";
        final String dateTimeFormatString = "EEE, MMM d | h:mm aa";
        final long HOURS = 60 * 60 * 60;
        if (now.get(Calendar.DATE) == smsTime.get(Calendar.DATE)) {
            return context.getString(R.string.today) + " " + DateFormat.format(timeFormatString, smsTime);
        } else if (now.get(Calendar.DATE) - smsTime.get(Calendar.DATE) == 1) {
            return context.getString(R.string.yesterday) + " " + DateFormat.format(timeFormatString, smsTime);
        } else if (now.get(Calendar.YEAR) == smsTime.get(Calendar.YEAR)) {
            return DateFormat.format(dateTimeFormatString, smsTime).toString();
        } else {
            return DateFormat.format("MMM dd yyyy | h:mm aa", smsTime).toString();
        }
    }

    public static Dialog initProgressDialog(Context c) {
        Dialog dialog = new Dialog(c);
        dialog.setCanceledOnTouchOutside(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progress_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        return dialog;
    }

    public static void T(Context c, String msg) {
        Toast.makeText(c, msg, Toast.LENGTH_SHORT).show();
    }

    public static void share(Context c, String subject, String shareBody) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        c.startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    public static void T_Long(Context c, String msg) {
        Toast.makeText(c, msg, Toast.LENGTH_LONG);
    }

/*    public static void setLanguage(String language, Context context) {
        SavedData.saveLanguage(language);
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());
        LocaleHelper.setLocale(context, language);
    }*/

/*    public static void LoginDialog(Context context){
        BottomSheetDialog dialog = new BottomSheetDialog(context);
          binding = DialogLoginBinding.inflate(LayoutInflater.from(context));
        dialog.setContentView(binding.getRoot());
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.show();

        binding.rlDropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        binding.llGetOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (UserAccount.isValidPhoneNumber(binding.mobileEt,context)){
                    SignUpTask(context,dialog);
                }else {
                    UserAccount.EditTextPointer.setError(UserAccount.errorMessage);
                    UserAccount.EditTextPointer.requestFocus();
                }

            }
        });
    }

    public static void SignUpTask(Context context, BottomSheetDialog Logindialog) {
        Dialog progressDialog = Utils.initProgressDialog(context);
        mobile = binding.mobileEt.getText().toString().trim();
        apiService.SignUp(binding.mobileEt.getText().toString().trim()).enqueue(new Callback<AllResponseModel>() {
            @Override
            public void onResponse(Call<AllResponseModel> call, Response<AllResponseModel> response) {
                progressDialog.dismiss();
                if (response.code() == StatusCodeConstant.OK){
                    Key = response.body().key;
                    Logindialog.dismiss();
                    verifyDialog(response.body().otp,context);
                    FirebaseService.generateToken(context);

                }else {
                    assert response.errorBody() != null;
                    APIError apiError = new Gson().fromJson(response.errorBody().charStream(), APIError.class);
                    Utils.alert(context, apiError.message());
                    if (response.code() == Constants.Unauthorized) {
                        Utils.UnAuthorizationToken(context);
                    }
                }
            }
            @Override
            public void onFailure(Call<AllResponseModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    public static void verifyDialog(String Otp, Context context) {
        BottomSheetDialog dialog = new BottomSheetDialog(context);
             verificationBinding = DialogOtpVerificationBinding.inflate(LayoutInflater.from(context));
        dialog.setContentView(verificationBinding.getRoot());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.show();
        verificationBinding.PinView.setText(Otp);
        verificationBinding.resendTv.setEnabled(false);
        verificationBinding.resendTv.setTextColor(ContextCompat.getColor(context, R.color.text_secondary));

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        countDownTimer = new CountDownTimer(20000, 1000) {
            @SuppressLint("DefaultLocale")
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                verificationBinding.timeRemainingTv.setText(String.format("%s:%s %s", String.format("%02d", minutes),
                        String.format("%02d", seconds),context.getString(R.string.sec_left)));
            }

            @Override
            public void onFinish() {
                verificationBinding.timeRemainingTv.setVisibility(View.GONE);
                verificationBinding.resendTv.setEnabled(true);
                verificationBinding.resendTv.setTextColor(ContextCompat.getColor(context, R.color.text_blue));
            }
        }.start();

        verificationBinding.resendTv.setOnClickListener(v -> {
            verificationBinding.resendTv.setEnabled(false);

            verificationBinding.timeRemainingTv.setVisibility(View.VISIBLE);

            ResendOtp(context);

            verificationBinding.resendTv.setTextColor(ContextCompat.getColor(context, R.color.text_secondary));
            countDownTimer = new CountDownTimer(20000, 1000) {
                @SuppressLint("DefaultLocale")
                @Override
                public void onTick(long millisUntilFinished) {
                    int seconds = (int) (millisUntilFinished / 1000);
                    int minutes = seconds / 60;
                    seconds = seconds % 60;
                    verificationBinding.timeRemainingTv.setText(String.format("%s:%s %s",
                            String.format("%02d", minutes),
                            String.format("%02d", seconds), context.getString(R.string.sec_left)));
                }

                @Override
                public void onFinish() {
                    verificationBinding.timeRemainingTv.setVisibility(View.GONE);
                    verificationBinding.resendTv.setEnabled(true);


                    verificationBinding.resendTv.setTextColor(ContextCompat.getColor(context, R.color.text_blue));
                }
            }.start();
            // ResendOtpTask();
        });

        verificationBinding.tvNumber.setText(binding.mobileEt.getText().toString());

        verificationBinding.tvGetOTPBtn.setOnClickListener(v -> {
            dialog.dismiss();
            Utils.E("SaveFCMId:::"+ SavedData.getFirebaseToken());

            if (UserAccount.isEmpty(context, verificationBinding.PinView)) {
                MatchOtpTask(verificationBinding.PinView.getText().toString().trim(),context,dialog);
            } else {
                UserAccount.EditTextPointer.setError(UserAccount.errorMessage);
                UserAccount.EditTextPointer.requestFocus();
            }
        });

        dialog.setCancelable(true);
    }

    public static  void MatchOtpTask(String Otp, Context context, BottomSheetDialog verifydialog) {
        Dialog progressDialog = Utils.initProgressDialog(context);
        apiService.MatchOtp(mobile,SavedData.getFirebaseToken(),Otp).enqueue(new Callback<AllResponseModel>() {
            @Override
            public void onResponse(Call<AllResponseModel> call, Response<AllResponseModel> response) {
                progressDialog.dismiss();
                if (response.code()==StatusCodeConstant.OK){
                    UserModel userModel;

                    if(Utils.IS_LOGIN())
                        userModel =  UserDataHelper.getInstance().getList().get(0);
                    else  userModel = new UserModel();

                    userModel.UserID = response.body().matchOTPData.userId;
                    userModel.UserToken = response.body().matchOTPData.token;
                    userModel.UserMobile = response.body().matchOTPData.mobile;
                    UserDataHelper.getInstance().insertData(userModel);
                    userId = response.body().matchOTPData.userId;

                    if (Key.equals(Constants.Login)){
                        verifydialog.dismiss();
                    }else {
                        ReffralDialog(context);
                    }
                }else {
                    assert response.errorBody() != null;
                    APIError apiError = new Gson().fromJson(response.errorBody().charStream(), APIError.class);
                    Utils.alert(context, apiError.message());
                    if (response.code() == Constants.Unauthorized) {
                        Utils.UnAuthorizationToken(context);
                    }
                }
            }

            @Override
            public void onFailure(Call<AllResponseModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    public static void ResendOtp(Context context) {
        Dialog progressDialog = Utils.initProgressDialog(context);
        apiService.ResendOtp(mobile).enqueue(new Callback<AllResponseModel>() {
            @Override
            public void onResponse(Call<AllResponseModel> call, Response<AllResponseModel> response) {
                progressDialog.dismiss();
                if (response.code()==StatusCodeConstant.OK){
                    verificationBinding.PinView.setText(response.body().otp);
                }else {
                    assert response.errorBody() != null;
                    APIError apiError = new Gson().fromJson(response.errorBody().charStream(), APIError.class);
                    Utils.alert(context, apiError.message());
                    if (response.code() == Constants.Unauthorized) {
                        Utils.UnAuthorizationToken(context);
                    }
                }
            }
            @Override
            public void onFailure(Call<AllResponseModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    public static void ReffralDialog(Context context) {
        BottomSheetDialog dialog = new BottomSheetDialog(context);
        dialogRefferalBinding = DialogRefferalBinding.inflate(LayoutInflater.from(context));
        dialog.setContentView(dialogRefferalBinding.getRoot());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.show();
        dialogRefferalBinding.tvConfirmCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserAccount.isEmpty(context,dialogRefferalBinding.etReferral)){
                    RefferalTask(context);
                }else {
                    UserAccount.EditTextPointer.setError(UserAccount.errorMessage);
                    UserAccount.EditTextPointer.requestFocus();
                }
            }
        });

        dialogRefferalBinding.tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.I(context,DashboardActivity.class,null);
            }
        });
    }

    public static  void RefferalTask(Context context) {
        Dialog progressDialog = Utils.initProgressDialog(context);
        apiService.RefferalSignUp(userId,dialogRefferalBinding.etReferral.getText().toString().trim()).enqueue(new Callback<AllResponseModel>() {
            @Override
            public void onResponse(Call<AllResponseModel> call, Response<AllResponseModel> response) {
                progressDialog.dismiss();
                if (response.code()==StatusCodeConstant.OK){
                    Utils.I(context,DashboardActivity.class,null);
                }else {
                    assert response.errorBody() != null;
                    APIError apiError = new Gson().fromJson(response.errorBody().charStream(), APIError.class);
                    Utils.alert(context, apiError.message());
                    if (response.code() == Constants.Unauthorized) {
                        Utils.UnAuthorizationToken(context);
                    }
                }
            }

            @Override
            public void onFailure(Call<AllResponseModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    public static AlertDialog.Builder deleteCartAlert(Context context,String Message,String cartId){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.app_name).setMessage(Message)
                .setPositiveButton(R.string.ok, (dialog, which) -> DeletCart(cartId))
                .setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss()).show();
        return builder;

    }

    private static void DeletCart(String cartId) {
        apiService.deletCart(Utils.IS_LOGIN() ? Utils.GetSession().UserToken : Constants.beforeLogin,
                cartId).enqueue(new Callback<AllResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<AllResponseModel> call, @NonNull Response<AllResponseModel> response) {
                if (response.code() == StatusCodeConstant.OK) {

                }
            }

            @Override
            public void onFailure(@NonNull Call<AllResponseModel> call, @NonNull Throwable t) {
            }
        });
    }*/


}