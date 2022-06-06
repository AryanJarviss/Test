package com.example.globalmarket.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.globalmarket.databinding.ActivityPaymentBinding;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityPaymentBinding activityPaymentBinding;
    Activity activity=PaymentActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPaymentBinding=ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(activityPaymentBinding.getRoot());
        initialise();
    }

    private void initialise() {
        activityPaymentBinding.bakkk.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==activityPaymentBinding.bakkk){
            finish();
        }
    }
}