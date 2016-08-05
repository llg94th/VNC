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
    private View view, view2;
    private WindowManager.LayoutParams params, params2;
    private boolean isShown, isMove;

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
        isShown = false;
        view = LayoutInflater.from(this).inflate(R.layout.item_chathead, null);
        view2 = LayoutInflater.from(this).inflate(R.layout.item_notification_test, null);
        view2.setBackgroundColor(Color.BLUE);
//        chatHead = new ImageView(this);
//        chatHead.setImageResource(R.drawable.ic_vnc_logo);

        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        params2 = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.TOP | Gravity.START;
        params2.gravity = Gravity.TOP | Gravity.START;
        params2.windowAnimations = R.style.TestAnimation;
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
                        params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                        params.dimAmount = 0.7f;
                        windowManager.updateViewLayout(view, params);
                        isMove = false;
                        return true;
                    case MotionEvent.ACTION_UP:
                        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                        windowManager.updateViewLayout(view, params);;
                        if (!isMove) {
                            if (isShown) {
                                if (view2 != null) windowManager.removeView(view2);
                                isShown = false;
                            } else {
                                params2.x = params.x - view2.getWidth();
                                params2.y = params.y + view.getHeight();
                                windowManager.addView(view2, params2);
                                isShown = true;
                            }
                        }
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        isMove = true;
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);

                        if (isShown) {
                            params2.x = params.x - view2.getWidth();
                            params2.y = params.y + view.getHeight();
                            windowManager.updateViewLayout(view2, params2);
                        }
                        windowManager.updateViewLayout(view, params);

                        Log.d("MY_TAG", "(x,y) = (" + params.x + "," + params.y + ")");
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
