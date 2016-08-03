package com.example.ngoc.vncgiaohngpro.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ngoc.vncgiaohngpro.R;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class LoginActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {

    }

}
