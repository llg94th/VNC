package com.example.ngoc.vncgiaohngpro.sevices;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.ngoc.vncgiaohngpro.R;
import com.pkmmte.view.CircularImageView;

/**
 * Created by HUNGNTPH04073@FPT.EDU.VN on 8/4/2016.
 */
public class WMService extends Service {

    private WindowManager windowManager;
    private View view;
    private WindowManager.LayoutParams params;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MY_TAG", "WMService: onCreate()");
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        view = LayoutInflater.from(this).inflate(R.layout.item_chathead, null);
        ImageView img = (ImageView) view.findViewById(R.id.imgchat);
        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .withBorder(4).bold().fontSize(32) /* thickness in px */
                .endConfig().buildRound("VNC", Color.BLUE);
        img.setImageDrawable(drawable);
//        chatHead = new ImageView(this);
//        chatHead.setImageResource(R.drawable.ic_vnc_logo);

        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;

        windowManager.addView(view, params);

        view.setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = params.x;
                        initialY = params.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        view.setAlpha(0.5f);
                        windowManager.updateViewLayout(view, params);
                        return true;
                    case MotionEvent.ACTION_UP:
                        view.setAlpha(1.0f);
                        windowManager.updateViewLayout(view, params);
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);
                        windowManager.updateViewLayout(view, params);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MY_TAG", "WMService: onDestroy()");
        if (view != null) windowManager.removeView(view);
    }
}
