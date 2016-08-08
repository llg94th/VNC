package com.example.ngoc.vncgiaohngpro.views;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.ngoc.vncgiaohngpro.R;
import com.example.ngoc.vncgiaohngpro.objects.TestAdapter;
import com.example.ngoc.vncgiaohngpro.objects.TestListView;

import java.util.ArrayList;

/**
 * Created by phimau on 8/3/2016.
 */
public class Testlv extends AppCompatActivity {
    ListView lv;
    ArrayList<TestListView> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layouttest);
        lv = (ListView) findViewById(R.id.lv);
        list = new ArrayList<>();
        TestListView view1 = new TestListView(R.mipmap.ic_launcher, "Mầu Hồng Phi");
        TestListView view2 = new TestListView(R.mipmap.ic_launcher, "Mầu Văn Điệp");
        list.add(view1);
        list.add(view2);
        TestAdapter testAdapter = new TestAdapter(Testlv.this, R.layout.item_test, list);
        lv.setAdapter(testAdapter);
        ////////////////////// CONTACT///////
        ArrayList<ContentProviderOperation> ops =
                new ArrayList<ContentProviderOperation>();


        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null).build());

        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, "Nguyeenx ").build()
        );
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
//                .withValue(ContactsContract.Data._ID,"101")
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, "0169857427")
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                .build()
        );
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Organization.COMPANY,"VNC").build()
        );

        try {
            getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }

    }


}
