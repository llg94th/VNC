package com.example.ngoc.vncgiaohngpro.views;
/**
 * Created by HUNGNTPH04073@FPT.EDU.VN on 6/20/2016.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.ngoc.vncgiaohngpro.R;
import com.example.ngoc.vncgiaohngpro.VNC;
import com.example.ngoc.vncgiaohngpro.gcm.RegistrationIntentService;
import com.example.ngoc.vncgiaohngpro.objects.VNCNotification;

import java.util.ArrayList;

public class WebViewNotificationActivity extends AppCompatActivity {

    private int id;
    private WebView mweb;
    private ArrayList<VNCNotification> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notification);
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);

        innit();
        mweb.loadUrl(VNC.API_GET_NOTI_BY_ID + id);
        Toast.makeText(WebViewNotificationActivity.this, VNC.API_GET_NOTI_BY_ID + id, Toast.LENGTH_SHORT).show();
    }

    private void innit() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Thông báo Công ty");
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        id = getIntent().getIntExtra("id", 0);
        mweb = (WebView) findViewById(R.id.myweb);
    }
}
