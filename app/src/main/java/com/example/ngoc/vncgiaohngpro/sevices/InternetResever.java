package com.example.ngoc.vncgiaohngpro.sevices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by phimau on 8/8/2016.
 */
public class InternetResever extends BroadcastReceiver {
    static ChangeInternetListener changeInternetListener2;

    public static void setOnChangeInternetListener(ChangeInternetListener listener){
        changeInternetListener2 = listener;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"onReceive",Toast.LENGTH_SHORT).show();
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info!=null&info.isConnected()){
            changeInternetListener2.onChangeInternetListener(true);
        } else {
            changeInternetListener2.onChangeInternetListener(false);
        }
    }
    public interface ChangeInternetListener{
        public void onChangeInternetListener(boolean isConnect);
    }
}
