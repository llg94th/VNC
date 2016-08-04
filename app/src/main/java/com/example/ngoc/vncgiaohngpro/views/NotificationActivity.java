package com.example.ngoc.vncgiaohngpro.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import com.example.ngoc.vncgiaohngpro.R;
import com.example.ngoc.vncgiaohngpro.VNC;
import com.example.ngoc.vncgiaohngpro.adapters.NotificationAdapter;
import com.example.ngoc.vncgiaohngpro.objects.VNCNotification;
import com.example.ngoc.vncgiaohngpro.sevices.WMService;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    private ArrayList<VNCNotification> mList;
    private ListView lvNoti;
    NotificationAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        innit();
        loadListView();
    }

    private void innit() {
        mList = new ArrayList<>();
        lvNoti = (ListView) findViewById(R.id.lvNoti);
        adapter = new NotificationAdapter(NotificationActivity.this,mList);
        lvNoti.setAdapter(adapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Thông báo Công ty");
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        startService(new Intent(NotificationActivity.this, WMService.class));
    }
    private void loadListView(){
        Ion.with(NotificationActivity.this).load(VNC.API_GET_LIST_NOTI).asString().setCallback(new FutureCallback<String>() {
            @Override
            public void onCompleted(Exception e, String result) {
                if (e==null){
                    Log.d("MY_TAG",result);
                    try {
                        JSONArray jArray = new JSONArray(result);
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject jOjb = jArray.getJSONObject(i);
                            Log.d("MY_TAG",i+": "+jOjb.getString("tit"));
                            VNCNotification noti = new VNCNotification(jOjb.getInt("id"),jOjb.getString("tit"),jOjb.getLong("time"));
                            mList.add(noti);
                        }
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                }else {
                    Log.d("MY_TAG","Ion errror: "+e.toString());
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(NotificationActivity.this, WMService.class));
    }
}
