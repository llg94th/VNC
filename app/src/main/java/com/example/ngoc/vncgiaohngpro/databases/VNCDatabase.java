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

    //   ***********Bảng Contact *********
    public final static String TBL_CONTACT = "contact";
    public final static String COL_CONTACT_ID = "id";
    public final static String COL_CONTACT_NAME = "name";
    public final static String COL_CONTACT_NUMPHONE = "numphone";
    public final static String COL_CONTACT_POSITION = "position";


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
        String sqlCreat3 =
                "CREATE TABLE "
                        + TBL_CONTACT + " ( "
                        + COL_CONTACT_ID + " VARCHAR(11)NOT NULL PRIMARY KEY,"
                        + COL_CONTACT_NAME + " TEXT, "
                        + COL_CONTACT_NUMPHONE + " VARCHAR(12), "
                        + COL_CONTACT_POSITION + " TEXT )";
        db.execSQL(sqlCreat1);
        db.execSQL(sqlCreat2);
        db.execSQL(sqlCreat3);

    }

    public void updateContact(ArrayList<VNCContact> list){
        db=getWritableDatabase();
        String sql = "delete from "+TBL_CONTACT;
        db.execSQL(sql);
        for (int i=0;i<list.size();i++){
            ContentValues values = new ContentValues();
            values.put(COL_CONTACT_ID,list.get(i).getId());
            values.put(COL_CONTACT_NAME,list.get(i).getName());
            values.put(COL_CONTACT_NUMPHONE,list.get(i).getNumPhone());
            values.put(COL_CONTACT_POSITION,list.get(i).getPosition());
            db.insert(TBL_CONTACT,null,values);
        }
        db.close();
    }
    public VNCContact getContact(String id){
        db= getReadableDatabase();
        String sql = "Select * from "+TBL_CONTACT+"Where id='"+id+"'";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        String name = cursor.getString(cursor.getColumnIndex(COL_CONTACT_NAME));
        String numphone = cursor.getString(cursor.getColumnIndex(COL_CONTACT_NUMPHONE));
        String position = cursor.getString(cursor.getColumnIndex(COL_CONTACT_POSITION));
        VNCContact contact = new VNCContact(id,name,numphone,position);
        db.close();
        return contact;
    }

    public ArrayList<VNCContact> getAllContact(){
        ArrayList<VNCContact> list = new ArrayList<>();
        db= getReadableDatabase();
        String sql = "Select * from "+TBL_CONTACT;

        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            String id  =cursor.getString(cursor.getColumnIndex(COL_CONTACT_ID));
            String name = cursor.getString(cursor.getColumnIndex(COL_CONTACT_NAME));
            String numphone = cursor.getString(cursor.getColumnIndex(COL_CONTACT_NUMPHONE));
            String position = cursor.getString(cursor.getColumnIndex(COL_CONTACT_POSITION));
            VNCContact contact = new VNCContact(id,name,position,numphone);
            list.add(contact);
        }
        return list;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
