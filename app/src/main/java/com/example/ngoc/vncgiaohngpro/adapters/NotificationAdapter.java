package com.example.ngoc.vncgiaohngpro.adapters;

import android.content.Context;
import android.content.Intent;
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
import com.example.ngoc.vncgiaohngpro.objects.VNCNotification;
import com.example.ngoc.vncgiaohngpro.views.WebViewNotificationActivity;

import java.util.ArrayList;

/**
 * Created by HUNGNTPH04073@FPT.EDU.VN on 8/3/2016.
 */
public class NotificationAdapter extends ArrayAdapter<VNCNotification> {
    private Context mContext;
    private ArrayList<VNCNotification> mList;

    public NotificationAdapter(Context context, ArrayList<VNCNotification> list) {
        super(context, R.layout.item_notification, list);
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VNCNotification notification = mList.get(position);
        ViewHolder holder = new ViewHolder(convertView);
        convertView = holder.setViewWithData(notification);
        return convertView;
    }

    private class ViewHolder {
        private TextView tv1, tv2;
        private ImageView img;
        private View mView;

        public ViewHolder(View view) {
            if (view == null) {
                mView = LayoutInflater.from(mContext).inflate(R.layout.item_notification,null);
            }else {
                mView = view;
            }
            tv1 = (TextView) mView.findViewById(R.id.tvNotiName);
            tv2 = (TextView) mView.findViewById(R.id.tvNotiTime);
            img = (ImageView) mView.findViewById(R.id.ivNotiThum);
        }

        public View setViewWithData(final VNCNotification noti) {
            String time = VNC.convertTimeToString(noti.getTime());
            tv1.setText(noti.getName());
            tv2.setText(time);
            ColorGenerator generator = ColorGenerator.MATERIAL;
            TextDrawable drawable = TextDrawable.builder().buildRound(time.substring(0,2),generator.getRandomColor());
            img.setImageDrawable(drawable);
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, WebViewNotificationActivity.class);
                    intent.putExtra("id",noti.getId());
                    mContext.startActivity(intent);
                }
            });
            return mView;
        }
    }
}
