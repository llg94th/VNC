package com.example.ngoc.vncgiaohngpro.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ngoc.vncgiaohngpro.objects.VNCContact;

import java.util.ArrayList;

/**
 * Created by HUNGNTPH04073@FPT.EDU.VN on 6/20/2016.
 */
public class VNCDatabase extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    public static final String DATA_NAME = "VNCBills";
    /* Bảng hoá đơn */
    public final static String TBL_VNC_DEAL = "deals";
    public final static String COL_ORDER_NUMBER = "orderNum";
    public final static String COL_ID = "id";
    public final static String COL_CITY = "city";
    public final static String COL_PROVINCE = "province";
    public final static String COL_AREA = "area";
    public final static String COL_ADDRESS = "address";
    public final static String COL_CUSTOMER_NAME = "customerName";
    public final static String COL_PHONE_NUMBER_1 = "phoneNum1";
    public final static String COL_PHONE_NUMBER_2 = "phoneNum2";
    public final static String COL_PRODUCT_NAME = "productName";
    public final static String COL_PROMOTION = "promotion";
    public final static String COL_PRODUCT_PRICE = "price";
    public final static String COL_INVENTORY_DAYS = "inventoryDays";
    public final static String COL_EMPLOYER = "employer";
    public final static String COL_STATUS = "status";
    public final static String COL_POINT = "point";
    public final static String COL_NOTE = "note";
    public final static String COL_SEASION = "seasion";
    public final static String COL_UPDATE_TIME = "updateTime";

    /* Bảng phiên giao hàng */
    public final static String TBL_SEASION = "seasion";
    public final static String COL_SEASION_ID = "id";
    public final static String COL_SEASION_NAME = "name";
    public final static String COL_START_TIME = "start";
    public final static String COL_END_TIME = "end";




    public VNCDatabase(Context context) {
        super(context, DATA_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCreat1 =
                "CREATE TABLE "
                        + TBL_VNC_DEAL + "( "
                        + COL_ID + " TEXT NOT NULL PRIMARY KEY, "
                        + COL_ORDER_NUMBER + " INTEGER, "
                        + COL_CITY + " TEXT, "
                        + COL_PROVINCE + " TEXT, "
                        + COL_AREA + " TEXT, "
                        + COL_ADDRESS + " TEXT, "
                        + COL_CUSTOMER_NAME + " TEXT, "
                        + COL_PHONE_NUMBER_1 + " TEXT, "
                        + COL_PHONE_NUMBER_2 + " TEXT, "
                        + COL_PRODUCT_NAME + " TEXT, "
                        + COL_PROMOTION + " TEXT, "
                        + COL_PRODUCT_PRICE + " TEXT, "
                        + COL_INVENTORY_DAYS + " TEXT, "
                        + COL_STATUS + " INTEGER, "
                        + COL_EMPLOYER + " TEXT, "
                        + COL_POINT + " INTEGER, "
                        + COL_NOTE + " TEXT, "
                        + COL_SEASION + " TEXT, "
                        + COL_UPDATE_TIME + " INTEGER)";
        String sqlCreat2 =
                "CREATE TABLE "
                        + TBL_SEASION + " ( "
                        + COL_SEASION_ID + " INTEGER NOT NULL PRIMARY KEY, "
                        + COL_SEASION_NAME + " TEXT,"
                        + COL_START_TIME + " INTEGER, "
                        + COL_END_TIME + " INTEGER)";

        db.execSQL(sqlCreat1);
        db.execSQL(sqlCreat2);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
