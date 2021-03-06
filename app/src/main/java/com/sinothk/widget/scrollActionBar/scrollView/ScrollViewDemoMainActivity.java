package com.sinothk.widget.scrollActionBar.scrollView;

/**
 * @ author LiangYT
 * @ create 2018/9/29 9:35
 * @ Describe
 */

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinothk.widget.scrollActionBar.demo.R;
import com.sinothk.widget.scrollActionbar.scrollView.ObservableScrollView;
import com.sinothk.widget.scrollActionbar.uitls.ViewUtils;


public class ScrollViewDemoMainActivity extends Activity {

    private ObservableScrollView scrollView;

    private ImageView imageView;

    private RelativeLayout titleBarLayout;
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_view_demo_activity_main1);
        ViewUtils.setImmersionStateMode(this);

        titleBarLayout = (RelativeLayout) findViewById(R.id.titleBarLayout);
        textview = (TextView) findViewById(R.id.textview);

        scrollView = (ObservableScrollView) findViewById(R.id.scrollview);
        imageView = (ImageView) findViewById(R.id.imageview);

        scrollView.setOnScrollChanging(imageView, new ObservableScrollView.OnOperateListener() {
            @Override
            public void onStart() {
                titleBarLayout.setBackgroundColor(Color.argb((int) 0, 129, 129, 129));//AGB由相关工具获得，或者美工提供
                textview.setTextColor(Color.argb((int) 0, 255, 255, 255));
            }

            @Override
            public void onChanging(float alpha) {
                titleBarLayout.setBackgroundColor(Color.argb((int) alpha, 255, 64, 129));
                textview.setTextColor(Color.argb((int) alpha, 255, 255, 255));
            }

            @Override
            public void onEnd() {
                titleBarLayout.setBackgroundColor(Color.argb((int) 255, 255, 64, 129));

                textview.setTextColor(Color.argb((int) 255, 255, 255, 255));
            }
        });
    }
}

