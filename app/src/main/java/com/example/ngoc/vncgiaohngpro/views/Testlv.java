package com.example.ngoc.vncgiaohngpro.views;

import android.os.Bundle;
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
        lv= (ListView) findViewById(R.id.lv);
        list= new ArrayList<>();
        TestListView view1= new TestListView(R.mipmap.ic_launcher,"Mầu Hồng Phi");
        TestListView view2= new TestListView(R.mipmap.ic_launcher,"Mầu Văn Điệp");
        list.add(view1);
        list.add(view2);
        TestAdapter testAdapter = new TestAdapter(Testlv.this,R.layout.item_test,list);
        lv.setAdapter(testAdapter);
    }
}
