package com.example.globalmarket.UtilityTools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.globalmarket.R;
import com.google.firebase.database.annotations.NotNull;


/**
 * Created by Anil on 12/3/2021.
 */
public class UserAccount {
    @SuppressLint("StaticFieldLeak")
    public static EditText EditTextPointer;
    public static String errorMessage;

    /**
     * Email All Type Validation
     * @param editText//
     * @return //
     */
    public static boolean isEmailValid(@NotNull EditText editText, Context context) {
        //add your own logic
        if (TextUtils.isEmpty(editText.getText().toString().trim())) {
            EditTextPointer = editText;
            EditTextPointer.requestFocus();
            errorMessage = context.getString(R.string.empty_error);
            return false;
        } else {
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(editText.getText().toString().trim()).matches()) {
                return true;
            } else {
                EditTextPointer = editText;
                EditTextPointer.requestFocus();
                errorMessage = context.getString(R.string.invalid_email);
                return false;
            }
        }
    }


    /**
     * Mobile All Type Validation
     * @param editText//
     * @return //
     */
    public static boolean isValidPhoneNumber(@NotNull EditText editText, Context context) {
        if (editText.getText() == null || TextUtils.isEmpty(editText.getText())) {
            errorMessage = context.getString(R.string.empty_error);
            EditTextPointer = editText;
            EditTextPointer.requestFocus();
            return false;
        } else {
            if (editText.getText().toString().length() != 10) {
                EditTextPointer = editText;
                EditTextPointer.requestFocus();
                errorMessage = context.getString(R.string.enter_ten_digits_number);
                return false;
            } else {
                if (android.util.Patterns.PHONE.matcher(editText.getText()).matches()) {
                    return true;
                } else {
                    EditTextPointer = editText;
                    errorMessage = context.getString(R.string.valid_number);
                    return false;
                }
            }
        }
    }

    /**
     * Check Is Empty
     * @param arg //
     * @return //
     */
    public static boolean isEmpty(Context context,@NotNull EditText... arg) {
        for (EditText editText : arg) {
            if (editText.getText().toString().trim().length() <= 0) {
                EditTextPointer = editText;
                EditTextPointer.requestFocus();
                errorMessage = context.getString(R.string.empty_error);
                return false;
            }
        }
        return true;
    }


}