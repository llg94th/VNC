package com.example.ngoc.vncgiaohngpro.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ngoc.vncgiaohngpro.objects.VNCContact;

import java.util.ArrayList;

/**
 * Created by phimau on 8/8/2016.
 */
public class VNContactDatabase extends SQLiteOpenHelper {
    private  SQLiteDatabase db;

    //   ***********Bảng Contact *********
    public final static String TBL_CONTACT = "contact";
    public final static String COL_CONTACT_ID = "id";
    public final static String COL_CONTACT_NAME = "name";
    public final static String COL_CONTACT_NUMPHONE = "numphone";
    public final static String COL_CONTACT_POSITION = "position";

    public VNContactDatabase(Context context) {
        super(context, "contactdatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreat3 =
                "CREATE TABLE "
                        + TBL_CONTACT + " ( "
                        + COL_CONTACT_ID + " VARCHAR(11)NOT NULL PRIMARY KEY,"
                        + COL_CONTACT_NAME + " TEXT, "
                        + COL_CONTACT_NUMPHONE + " VARCHAR(12), "
                        + COL_CONTACT_POSITION + " TEXT )";
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
