package com.kshitizbali.twineup.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kshitizbali.twineup.utilities.ConstantUtils;
import com.kshitizbali.twineup.utilities.Utils;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utils.initializeStetho(getApplicationContext());

        if (Utils.isInternetAvailable(SplashScreen.this)) {
            Intent intent = new Intent(this, ScrollingActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(SplashScreen.this, ConstantUtils.NOT_CONNECTED_TO_THE_INTERNET, Toast.LENGTH_LONG).show();
        }


    }
}