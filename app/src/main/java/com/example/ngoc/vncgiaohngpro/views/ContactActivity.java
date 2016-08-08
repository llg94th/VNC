package com.example.ngoc.vncgiaohngpro.views;

import android.content.ContentProviderOperation;
import android.content.OperationApplicationException;
import android.graphics.Color;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.ngoc.vncgiaohngpro.R;
import com.example.ngoc.vncgiaohngpro.VNC;
import com.example.ngoc.vncgiaohngpro.adapters.ContactAdapter;
import com.example.ngoc.vncgiaohngpro.databases.VNCDatabase;
import com.example.ngoc.vncgiaohngpro.objects.VNCContact;
import com.example.ngoc.vncgiaohngpro.preference.LoginPreference;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {
    private LoginPreference preference;
    private Button btn_Update;
    private Toolbar toolbar;
    private VNCDatabase database;
    private ContactAdapter adapter;
    private ArrayList<VNCContact> list;
    private ListView lv_Contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        init();
        setupToolbar();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (preference.isFirstLogined()){
            updateContact();
        }

    }

    private void setupToolbar() {

    }

    private void init() {
        preference = new LoginPreference(getBaseContext());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        lv_Contact = (ListView) findViewById(R.id.lv_Contact);
        database = new VNCDatabase(getBaseContext());
        list = new ArrayList<>();
        list = database.getAllContact();
        adapter = new ContactAdapter(getBaseContext(),0,list);
        lv_Contact.setAdapter(adapter);
    }

    private void updateContact() {
        Ion.with(getBaseContext())
                .load(VNC.API_GETCONTACT)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        try {
                            ArrayList<VNCContact> listContact = new ArrayList<VNCContact>();
                            JSONObject object = new JSONObject(result);
                            JSONArray jsonContacts = object.getJSONArray("contact");
                            for (int i =0;i<jsonContacts.length();i++){
                                String id = jsonContacts.getJSONObject(i).getString("id");
                                String name = jsonContacts.getJSONObject(i).getString("name");
                                String postion = jsonContacts.getJSONObject(i).getString("position");
                                String numPhone = jsonContacts.getJSONObject(i).getString("numPhone");
                                VNCContact vncContact = new VNCContact(id,name,postion,numPhone);
                                listContact.add(vncContact);
                            }
                            saveContact(listContact);
                            database.updateContact(listContact);
                            list = database.getAllContact();
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                });

    }

    private void saveContact(ArrayList<VNCContact> listContact) {
        for (int i =0;i<listContact.size();i++){
            ArrayList<ContentProviderOperation> ops =
                    new ArrayList<ContentProviderOperation>();

            ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null).build());

            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, listContact.get(i).getName()).build()
            );
            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
    //                .withValue(ContactsContract.Data._ID,"101")
                            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                            .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, listContact.get(i).getNumPhone())
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_upDate:
                updateContact();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
