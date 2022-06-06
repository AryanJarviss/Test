package com.example.globalmarket.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.globalmarket.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityProfileBinding activityProfileBinding;
    Activity activity=ProfileActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProfileBinding=ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(activityProfileBinding.getRoot());
        initialise();
    }

    private void initialise() {
        activityProfileBinding.backs.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==activityProfileBinding.backs){
         finish();
        }
    }
}