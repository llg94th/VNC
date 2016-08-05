package com.example.ngoc.vncgiaohngpro;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by HUNGNTPH04073@FPT.EDU.VN on 8/3/2016.
 */
public class VNC {
    public final static String API_GET_LIST_NOTI = "http://192.168.1.28/VNCCapital/notification/get-list.php";
    public final static String API_GET_NOTI_BY_ID = "localhost/VNCCapital/notification/template.php?noti_id=";
    public final static String API_LOGIN = "http://192.168.1.182/vncapital/login.php?id=";
    public final static String API_SENDTOKEN = "http://192.168.1.182/vncapital/SetToken.php?id=";
    public static String convertTimeToString(long time){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTimeInMillis(time);
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        return formater.format(calendar.getTime());
    }
}
