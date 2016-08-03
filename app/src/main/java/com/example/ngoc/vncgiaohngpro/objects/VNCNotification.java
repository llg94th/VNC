package com.example.ngoc.vncgiaohngpro.objects;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.example.ngoc.vncgiaohngpro.VNC;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by HUNGNTPH04073@FPT.EDU.VN on 8/3/2016.
 */
public class VNCNotification {
    private int id;
    private String name;
    private String content;
    private long time;

    public VNCNotification() {
    }

    public VNCNotification(int id, String name, long time) {
        this.id = id;
        this.name = name;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        byte[] data = Base64.decode(name,Base64.DEFAULT);
        try {
            return new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return name;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
    public static ArrayList<VNCNotification> getListNotiFromSever(Context context){
        final ArrayList<VNCNotification> list = new ArrayList<>();
        Ion.with(context).load(VNC.API_GET_LIST_NOTI).asString().setCallback(new FutureCallback<String>() {
            @Override
            public void onCompleted(Exception e, String result) {
                Log.d("MY_TAG",result);
                try {
                    JSONArray jArray = new JSONArray(result);
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jOjb = jArray.getJSONObject(i);
                        Log.d("MY_TAG",i+": "+jOjb.getString("tit"));
                        VNCNotification noti = new VNCNotification(jOjb.getInt("id"),jOjb.getString("tit"),jOjb.getLong("time"));
                        list.add(noti);
                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        });
        return list;
    }
}
