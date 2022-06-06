package com.example.globalmarket.Activity;

import static com.example.globalmarket.R.drawable.draw1;
import static com.example.globalmarket.R.drawable.draw2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.globalmarket.R;
import com.example.globalmarket.UtilityTools.Utils;
import com.example.globalmarket.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityMainBinding activityMainBinding;
    Activity activity=MainActivity.this;
    Boolean tab=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        initialise();
    }

    private void initialise() {
        activityMainBinding.cvcontinue.setOnClickListener(this);
        activityMainBinding.cvhome.setOnClickListener(this);
        activityMainBinding.cv4.setOnClickListener(this);
        activityMainBinding.cvthree.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view== activityMainBinding.cvcontinue){
            Utils.I(activity,WelcomeActivity.class,null);
        }
        else if(view==activityMainBinding.cvhome){
            finish();
            Utils.T(activity,"Back");
        }
        else if(view==activityMainBinding.cv4){
            if(tab==true){
                activityMainBinding.cv4.setBackgroundDrawable(getDrawable(draw1));
                activityMainBinding.tvh.setTextColor(getColor(R.color.fcb122));
                activityMainBinding.tvhindi.setTextColor(getColor(R.color.fcb122));

                activityMainBinding.cvthree.setBackgroundDrawable(getDrawable(draw2));
                activityMainBinding.tve.setTextColor(getColor(R.color.fcb1222));
                activityMainBinding.tvenglish.setTextColor(getColor(R.color.fcb1222));
                Utils.T(activity,"Hindi Selected");

            }
        }
        else if(view==activityMainBinding.cvthree){
            if(tab==true){
                activityMainBinding.cvthree.setBackgroundDrawable(getDrawable(draw1));
                activityMainBinding.tve.setTextColor(getColor(R.color.fcb122));
                activityMainBinding.tvenglish.setTextColor(getColor(R.color.fcb122));

                activityMainBinding.cv4.setBackgroundDrawable(getDrawable(draw2));
                activityMainBinding.tvh.setTextColor(getColor(R.color.fcb1222));
                activityMainBinding.tvhindi.setTextColor(getColor(R.color.fcb1222));
                Utils.T(activity,"English Selected");
            }
        }
    }
}