package com.example.ngoc.vncgiaohngpro.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.ngoc.vncgiaohngpro.R;
import com.example.ngoc.vncgiaohngpro.VNC;
import com.example.ngoc.vncgiaohngpro.objects.VNCContact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phimau on 8/8/2016.
 */
public class ContactAdapter extends ArrayAdapter<VNCContact> {
    private ArrayList<VNCContact> listContact;
    private Context context;

    public ContactAdapter(Context context, int resource, ArrayList<VNCContact> objects) {
        super(context, resource, objects);
        this.context =context;
        this.listContact=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.item_contact,null);
        VNCContact contact = getItem(position);

        ImageView img_name = (ImageView) convertView.findViewById(R.id.img_nameContact);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_position = (TextView) convertView.findViewById(R.id.tv_position);

        ColorGenerator generator = ColorGenerator.MATERIAL;
        String name = contact.getName();
        int color2 = generator.getColor(name);
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(name.substring(0,1), color2);
        img_name.setImageDrawable(drawable);
        tv_name.setText(contact.getName());
        tv_position.setText(contact.getPosition());
        return convertView;
    }
}
