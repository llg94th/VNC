package com.example.ngoc.vncgiaohngpro.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ngoc.vncgiaohngpro.R;
import com.example.ngoc.vncgiaohngpro.adapters.OrderAdapter;

import java.util.ArrayList;

public class ShippingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        OrderAdapter adt = new OrderAdapter(ShippingActivity.this,R.layout.item_order,list);
        ListView lv = (ListView) findViewById(R.id.lvTest);
        assert lv != null;
        lv.setAdapter(adt);
    }
}
