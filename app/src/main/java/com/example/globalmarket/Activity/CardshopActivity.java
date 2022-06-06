package com.example.globalmarket.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.globalmarket.UtilityTools.Utils;
import com.example.globalmarket.databinding.ActivityCardshopBinding;

public class CardshopActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityCardshopBinding activityCardshopBinding;
    Activity activity=CardshopActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCardshopBinding=ActivityCardshopBinding.inflate(getLayoutInflater());
        setContentView(activityCardshopBinding.getRoot());
        initialise();
    }

    private void initialise() {
        activityCardshopBinding.bak.setOnClickListener(this);
        activityCardshopBinding.cvpaynow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==activityCardshopBinding.bak){
            Utils.I(activity,CartActivity.class,null);
        }
        else if(view==activityCardshopBinding.cvpaynow){
            Utils.I(activity,PaymentActivity.class,null);
        }
    }

}