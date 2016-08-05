package com.example.ngoc.vncgiaohngpro.objects;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ngoc.vncgiaohngpro.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phimau on 8/3/2016.
 */
public class TestAdapter extends ArrayAdapter<TestListView> {
    ArrayList<TestListView> list;
    Activity context;

    public TestAdapter(Activity context, int resource, ArrayList<TestListView> objects) {
        super(context, resource, objects);
        this.context= context;
        list= objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater =context.getLayoutInflater();
        convertView = layoutInflater.inflate(R.layout.item_test,null);

        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        ImageView img = (ImageView) convertView.findViewById(R.id.img);
        TestListView listView = getItem(position);
        tv_name.setText(listView.name);
        img.setImageResource(listView.srcImg);

        return convertView;

    }
}
