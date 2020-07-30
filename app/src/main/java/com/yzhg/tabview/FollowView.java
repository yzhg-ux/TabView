package com.yzhg.tabview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

public class FollowView extends TextView {

    private Context context;
    private float x = 0;//点击时候坐标位置
    private float y = 0;
    private float startx;//自定义view的开始坐标位置
    private float statty;


    public FollowView(Context context) {
        this(context, null);
    }

    public FollowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FollowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //获取点击的xy坐标
                x = event.getRawX();
                y = event.getRawY();
                Log.d("mile","event.getRawX():"+event.getRawY());
                Log.d("mile","event.getRawY():"+event.getRawY());
                //新建一个Rect来获取当前view的坐标位置
                Rect rect = new Rect();
                this.getGlobalVisibleRect(rect);
                startx = (rect.left); //设置当前view x坐标位置

                //获取状态来的高度
                Rect frame = new Rect();
                ((Activity) getContext()).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
                int statusBarHeight = frame.top;
                statty = (rect.top - statusBarHeight);//设置当前view y坐标 需要减去状态来高度
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("mile","MotionEvent.ACTION_MOVE");

                //根据手指移动来算出移动的xy坐标
                this.setTranslationX(startx + event.getRawX() - x);
                this.setTranslationY(statty + event.getRawY() - y);
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }
}