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
    public final static String API_GETCONTACT = "http://192.168.1.182/vncapital/getContact.php";

    public static String convertTimeToString(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTimeInMillis(time);
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        return formater.format(calendar.getTime());
    }

    //Check sim's operator
    public static int SIM_VINA = 1;
    public static int SIM_VIETTEL = 2;
    public static int SIM_MOBI = 3;
    public static int SIM_UNKNOW = 0;

    public static int getSimOperator(String number) {
        if (
                number.startsWith("043")
                        || number.startsWith("048")
                        || number.startsWith("091")
                        || number.startsWith("094")
                        || number.startsWith("0123")
                        || number.startsWith("0124")
                        || number.startsWith("0125")
                        || number.startsWith("0127")
                        || number.startsWith("0129")
                ) {
            return SIM_VINA;
        } else if (
                number.startsWith("042")
                        || number.startsWith("046")
                        || number.startsWith("096")
                        || number.startsWith("097")
                        || number.startsWith("098")
                        || number.startsWith("016")
                        || number.startsWith("088")
                ) {
            return SIM_VIETTEL;
        }else if (
                number.startsWith("090")
                        || number.startsWith("093")
                        || number.startsWith("0120")
                        || number.startsWith("0121")
                        || number.startsWith("0122")
                        || number.startsWith("0126")
                        || number.startsWith("0128")
                ) {
            return SIM_MOBI;
        }
        return SIM_UNKNOW;
    }

    public static String longTimeToString(long time){
        long curentTime = System.currentTimeMillis();
        long xChange = curentTime - time;
        if (xChange>12*60*60*1000){
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(time);
            SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyy",Locale.US);
            return formater.format(cal.getTime());
        }else if(xChange>60*60*1000){
            int h = (int) (xChange/(60*60*1000));
            return h+" giờ trước";
        }else if(xChange>60*1000){
            int m = (int)(xChange/(60*1000));
            return m+" phút trước";
        }
        return "Chưa rõ thời gian";
    }
}
