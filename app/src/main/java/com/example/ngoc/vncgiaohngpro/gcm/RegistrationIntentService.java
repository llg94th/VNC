package com.example.ngoc.vncgiaohngpro.gcm;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.ngoc.vncgiaohngpro.VNC;
import com.example.ngoc.vncgiaohngpro.objects.Account;
import com.example.ngoc.vncgiaohngpro.views.LoginActivity;
import com.example.ngoc.vncgiaohngpro.views.Testlv;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by HUNGNTPH04073@FPT.EDU.VN on 7/25/2016.
 */
public class RegistrationIntentService extends IntentService {
    private Intent intent;
    private static final String TAG = "RegIntentService";
    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Make a call to Instance API
        FirebaseInstanceId instanceID = FirebaseInstanceId.getInstance();
        // request token that will be used by the server to send push notifications
        String token = instanceID.getToken();
        Log.d("MY_WATCH", "FCM Registration Token: " + token);
        this.intent = intent;
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        String id = intent.getStringExtra("id");
        String apiSendToken= VNC.API_SENDTOKEN+id+"&token="+token;
        Log.d("API SEND",apiSendToken);
        Ion.with(getBaseContext())
                .load(apiSendToken)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        int success = Integer.parseInt(result.get("settoken").toString());
                        if (success==1) {
                            Log.d("SEND_TOKEN", "SUCCESS");
                        } else {
                            Log.d("SEND_TOKEN","LỖi QUEO");
                        }
                    }
                });

    }
}
