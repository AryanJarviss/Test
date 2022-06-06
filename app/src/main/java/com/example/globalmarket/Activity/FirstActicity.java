package com.example.globalmarket.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.globalmarket.UtilityTools.Utils;
import com.example.globalmarket.databinding.ActivityFirstActicityBinding;

public class FirstActicity extends AppCompatActivity implements View.OnClickListener{

    ActivityFirstActicityBinding activityFirstActicityBinding;
    Activity activity=FirstActicity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFirstActicityBinding=ActivityFirstActicityBinding.inflate(getLayoutInflater());
        setContentView(activityFirstActicityBinding.getRoot());
        initialize();
    }

    private void initialize() {
        activityFirstActicityBinding.cvcon.setOnClickListener(this);
        activityFirstActicityBinding.cvcontinue.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==activityFirstActicityBinding.cvcon){
            Utils.I(activity,HomeActivity.class,null);

        }else if (view==activityFirstActicityBinding.cvcontinue){
            Utils.I(activity,MainActivity.class,null);
            Utils.T(activity,"Login");
        }
    }
}