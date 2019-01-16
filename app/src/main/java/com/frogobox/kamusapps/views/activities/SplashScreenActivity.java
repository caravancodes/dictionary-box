package com.frogobox.kamusapps.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.frogobox.kamusapps.R;
import com.frogobox.kamusapps.helpers.LoadDataHelper;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        // -----------------------------------------------------------------------------------------
        ProgressBar mProgressBar = findViewById(R.id.progress_bar);
        // -----------------------------------------------------------------------------------------
        new LoadDataHelper(this, mProgressBar).execute();
        // -----------------------------------------------------------------------------------------
    }

}
