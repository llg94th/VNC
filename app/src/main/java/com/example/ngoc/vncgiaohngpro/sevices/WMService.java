package com.example.ngoc.vncgiaohngpro.sevices;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.example.ngoc.vncgiaohngpro.R;

/**
 * Created by HUNGNTPH04073@FPT.EDU.VN on 8/4/2016.
 */
public class WMService extends Service {

    private WindowManager windowManager;
    private View viewHeader, viewBillDetail;
    private WindowManager.LayoutParams paramHeader, paramsBillDetail;
    private boolean billDetailIsShow, isMoved;
    private int screenWidth, screenHeight;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        innit();
        windowManager.addView(viewHeader, paramHeader);
        viewHeader.setOnTouchListener(new TouchListenerForHeader());
    }

    private void innit() {
        //get windowManager
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        //get screen Width, Height
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
        billDetailIsShow = false;
        viewHeader = LayoutInflater.from(this).inflate(R.layout.item_chathead, null);
        paramHeader = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        paramHeader.gravity = Gravity.TOP | Gravity.START;
        paramHeader.x = 0;
        paramHeader.y = 100;

        viewBillDetail = LayoutInflater.from(this).inflate(R.layout.item_notification_test, null);
        viewBillDetail.setBackgroundColor(Color.BLUE);
        paramsBillDetail = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        paramsBillDetail.gravity = Gravity.TOP | Gravity.START;

        paramsBillDetail.windowAnimations = R.style.TestAnimation;//Set animation style cho Layoutparam bill detail

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MY_TAG", "WMService: onDestroy()");
        if (viewHeader != null) windowManager.removeView(viewHeader);
    }

    private class TouchListenerForHeader implements View.OnTouchListener {
        private int initialX, initialY;//Toạ độ ban đầu của header
        private float initialTouchX, initialTouchY;//Toạ độ hàm lúc chạm của header

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    return actionDownListener(v, event);
                case MotionEvent.ACTION_UP:
                    return actionUpListener(v, event);
                case MotionEvent.ACTION_MOVE:
                    return actionMoveListener(v, event);
            }
            return false;
        }

        private boolean actionMoveListener(View v, MotionEvent event) {

            int xChange = (int) (event.getRawX() - initialTouchX);//Xác định "độ di chuyển x"
            int yChange = (int) (event.getRawY() - initialTouchY);//Xác định "độ di chuyển y"
            isMoved = Math.abs(xChange) > 5 || Math.abs(yChange) > 5;//Nếu abs(độ di chuyển)>5 => đã di chuyển
            paramHeader.x = initialX + xChange;//Xác định toạ độ mới
            if (paramHeader.x<0){
                paramHeader.x=0;
            }else if(paramHeader.x>screenWidth-viewHeader.getWidth()){
                paramHeader.x=screenWidth-viewHeader.getWidth();
            }
            paramHeader.y = initialY + yChange;//Xác định toạ độ mới
            if (paramHeader.y<0){
                paramHeader.y=0;
            }else if (paramHeader.y>screenHeight-viewHeader.getHeight()){
                paramHeader.y=screenHeight-viewHeader.getHeight();
            }

            if (billDetailIsShow) {//Nếu viewBillDetail đang hiển thị
                //paramsBillDetail.x = paramHeader.x - viewBillDetail.getWidth() / 2 + viewHeader.getWidth() / 2;//Xác định toạ độ x paramsBillDetail để căn giữa header
                paramsBillDetail.y = paramHeader.y + viewHeader.getHeight();//Xác định toạ độ y paramsBillDetail để nằm ngay dưới header
                if (paramsBillDetail.y<(viewHeader.getHeight())){//Nếu paramsBillDetail.y lên cao quá thì gán ngược lại để nằm ngay dưới header
                    paramsBillDetail.y = viewHeader.getHeight();
                }else if (paramsBillDetail.y>screenHeight-viewBillDetail.getHeight()){//Nếu paramsBillDetail.y xuống quá thì gán ngược lại để năm bên trên bottom của screen
                    paramsBillDetail.y = screenHeight-viewBillDetail.getHeight();
                }
                windowManager.updateViewLayout(viewBillDetail, paramsBillDetail);
                if (paramHeader.y>screenHeight-viewHeader.getHeight()-viewBillDetail.getHeight()){
                    paramHeader.y=screenHeight-viewHeader.getHeight()-viewBillDetail.getHeight();
                }
            }
            windowManager.updateViewLayout(viewHeader, paramHeader);

            Log.d("MY_TAG", "(x,y) = (" + paramHeader.x + "," + paramHeader.y + ")");
            return true;
        }

        private boolean actionUpListener(View v, MotionEvent event) {
            paramHeader.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;//Kết thúc touch thì chuyển param thành FLAG_NOT_FOCUSABLE
            if (!isMoved) {
                if (billDetailIsShow) {
                    if (viewBillDetail != null)
                        windowManager.removeView(viewBillDetail);
                    billDetailIsShow = false;
                } else {
                    //Xác định toạ độ param2 để căn giữa view1
                    //paramsBillDetail.x = paramHeader.x - viewBillDetail.getWidth() / 2 + viewHeader.getWidth() / 2;
                    paramsBillDetail.x=0;
                    paramsBillDetail.y = paramHeader.y + viewHeader.getHeight();//Xác định toạ độ y paramsBillDetail để nằm ngay dưới header
                    if (paramsBillDetail.y<(viewHeader.getHeight())){//Nếu paramsBillDetail.y lên cao quá thì gán ngược lại để nằm ngay dưới header
                        paramsBillDetail.y = viewHeader.getHeight();
                    }else if (paramsBillDetail.y>screenHeight-viewBillDetail.getHeight()){//Nếu paramsBillDetail.y xuống quá thì gán ngược lại để năm bên trên bottom của screen
                        paramsBillDetail.y = screenHeight-viewBillDetail.getHeight();
                    }
                    windowManager.addView(viewBillDetail, paramsBillDetail);
                    billDetailIsShow = true;
                }
            }
            if (!billDetailIsShow){
                if (paramHeader.x<screenWidth/2){
                    paramHeader.x = 0;
                }else {
                    paramHeader.x = screenWidth - viewHeader.getWidth();
                }
            }
            windowManager.updateViewLayout(viewHeader, paramHeader);
            return true;
        }

        private boolean actionDownListener(View v, MotionEvent event) {
            initialX = paramHeader.x;
            initialY = paramHeader.y;
            initialTouchX = event.getRawX();
            initialTouchY = event.getRawY();
            //Bắt đầu touch thì chuyển param thành FLAG_DIM_BEHIND
            paramHeader.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            paramHeader.dimAmount = 0.7f;
            windowManager.updateViewLayout(viewHeader, paramHeader);
            isMoved = false;
            return true;
        }
    }
}
