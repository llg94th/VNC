package com.example.ngoc.vncgiaohngpro.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by HUNGNTPH04073@FPT.EDU.VN on 8/31/2016.
 */
public class OrderAdapter extends ArrayAdapter<Integer> {
    private Context mContext;
    private int mResource;

    public OrderAdapter(Context context, int resource, ArrayList<Integer> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mResource,null);
        }
        return convertView;
    }
}
