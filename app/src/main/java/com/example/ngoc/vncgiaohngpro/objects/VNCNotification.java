package com.example.ngoc.vncgiaohngpro.objects;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

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
        this.content = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        try {
            byte[] data = Base64.decode(name,Base64.DEFAULT);
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

}
