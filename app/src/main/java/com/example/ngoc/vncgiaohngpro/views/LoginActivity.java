package com.example.ngoc.vncgiaohngpro.views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ngoc.vncgiaohngpro.R;
import com.example.ngoc.vncgiaohngpro.VNC;
import com.example.ngoc.vncgiaohngpro.gcm.RegistrationIntentService;
import com.example.ngoc.vncgiaohngpro.objects.Account;
import com.example.ngoc.vncgiaohngpro.preference.LoginPreference;
import com.google.firebase.FirebaseApp;
import com.google.firebase.crash.FirebaseCrash;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText edt_id;
    private EditText edt_password;
    private Button btn_Login;
    private String apiLogin;
    private ProgressDialog progressDialog;
    private LoginPreference preference;
    private TextView tv_Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        isLogined();
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        tv_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Đã click",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void isLogined() {
        if (preference.isLogined()){
            String id = preference.getAccLogined().getId();
            String password = preference.getAccLogined().getPassword();
            edt_password.setText(password);
            edt_id.setText(id);
            ionLogin(id,password);
        }
    }

    private void login() {
        String id = edt_id.getText().toString();
        String password = edt_password.getText().toString();
        ionLogin(id,password);
        Log.d("LOGIN PREFERENCE","OKE");


    }

    private void ionLogin(final String id, final String password){
        progressDialog.show();
        String Api_Login = VNC.API_LOGIN + id + "&password=" + password;
        Ion.with(LoginActivity.this)
                .load(Api_Login)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        progressDialog.dismiss();
                        int success = Integer.parseInt(result.get("success").toString());
                        Log.d("LOGIN :", success + "");
                        if (success == 1) {
                            Log.d("LOGIN :", success + "1111");
                            sendTokent(id);
                            Intent i = new Intent(LoginActivity.this, ContactActivity.class);
                            startActivity(i);
                            preference.setLogin(new Account(id,password));
                        } else if (success == 0) {
                            Toast.makeText(getBaseContext(),"Tài khoản hoặc mật khẩu không hợp lệ",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    private void sendTokent(String id) {
        if (preference.isFirstLogined()){
            Log.d("sendTokent","1");
            Intent intent = new Intent(this, RegistrationIntentService.class);
            intent.putExtra("id",id);
            startService(intent);
        }
    }

    private void init() {
        edt_id = (EditText) findViewById(R.id.edt_id);
        edt_password = (EditText) findViewById(R.id.edt_password);
        btn_Login = (Button) findViewById(R.id.btn_login);
        tv_Register= (TextView) findViewById(R.id.tv_Register);
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Đăng nhập...");
        progressDialog.setCancelable(false);
        preference = new LoginPreference(getBaseContext());
        apiLogin = VNC.API_LOGIN;
        setUpFirebaseCrashReporting();
    }
    private void setUpFirebaseCrashReporting(){
        FirebaseCrash.report(new Exception("New Exeption"));
    }
}
