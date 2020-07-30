package com.yzhg.tabview;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    /**
     * @param savedInstanceState
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View viewCcroll = findViewById(R.id.view_scroll);

        LinearLayout llHorizontal = findViewById(R.id.llHorizontal);
        llHorizontal.measure(0, 0);
        final int measuredWidth = llHorizontal.getMeasuredWidth();
        Log.i("获取到LinearLayout可测量的宽度", "onCreate: " + measuredWidth);
        Log.i("获取到LinearLayout可测量的宽度", "onCreate: " + dip2px(this, 1000));

        ObservableScrollView svLayout = findViewById(R.id.svLayout);

        svLayout.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                float scaleX = scrollView.getScaleX();
                int width = scrollView.getWidth();
                //可滚动区域
                float left = ((float) x / (measuredWidth - width)) * dip2px(MainActivity.this, 180);
                Log.i("1", " ==:== " + x + " ==== ==== " + oldx);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewCcroll.getLayoutParams();
                params.leftMargin = (int) left;
                viewCcroll.setLayoutParams(params);
            }
        });

    }


    /**
     * dp与px之间的转换
     */
    public static int dip2px(Context context, float dip) {
        //获取屏幕的密度
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }


    public static float px2dip(Context context, float px) {
        float density = context.getResources().getDisplayMetrics().density;
        return px / density;
    }

}