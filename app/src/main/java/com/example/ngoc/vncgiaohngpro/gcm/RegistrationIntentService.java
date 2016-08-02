package com.example.ngoc.vncgiaohngpro.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by HUNGNTPH04073@FPT.EDU.VN on 7/25/2016.
 */
public class RegistrationIntentService extends IntentService {

    // abbreviated tag name
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

        // pass along this data
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        Log.d("MY_WATCH", "FCM Registration Token: " + token);
    }
}
