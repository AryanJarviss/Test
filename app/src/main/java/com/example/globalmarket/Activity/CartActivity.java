package com.example.globalmarket.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.globalmarket.UtilityTools.Utils;
import com.example.globalmarket.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity implements View.OnClickListener{
    ActivityCartBinding activityCartBinding;
    Activity activity=CartActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCartBinding=ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(activityCartBinding.getRoot());
        initialise();
    }

    private void initialise() {
        activityCartBinding.cvPaynow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {                           //All clicklistener used in this method
        if(view==activityCartBinding.cvPaynow){
            Utils.I(activity,CardshopActivity.class,null);
            Utils.T(activity,"Add to Cart");
        }
    }
}