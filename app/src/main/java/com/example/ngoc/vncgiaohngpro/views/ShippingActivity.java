package com.example.ngoc.vncgiaohngpro.views;
/**
 * Created by HUNGNTPH04073@FPT.EDU.VN on 6/20/2016.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.example.ngoc.vncgiaohngpro.R;
import com.example.ngoc.vncgiaohngpro.gcm.RegistrationIntentService;

public class ShippingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
        WebView mweb = (WebView) findViewById(R.id.myweb);
        assert mweb != null;
        mweb.loadUrl("file:///android_asset/www/index.html");
        mweb.setClickable(false);
        mweb.setLongClickable(false);
    }
}
