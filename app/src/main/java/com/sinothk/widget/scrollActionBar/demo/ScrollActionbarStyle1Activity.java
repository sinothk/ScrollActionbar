package com.sinothk.widget.scrollActionBar.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sinothk.widget.scrollActionbar.uitls.ViewUtils;

public class ScrollActionbarStyle1Activity extends AppCompatActivity {

    View mFLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_actionbar_style);

        ViewUtils.setImmersionStateMode(this);

        //ViewUtils.addStatuHeight(findViewById(R.id.fl_layout),this);

        AppBarLayout mAppBarLayout =  findViewById(R.id.appbar);
        mFLayout =  findViewById(R.id.fl_layout);

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                float percent = Float.valueOf(Math.abs(verticalOffset)) / Float.valueOf(appBarLayout.getTotalScrollRange());

                //第一种
                int toolbarHeight = appBarLayout.getTotalScrollRange();

                int dy = Math.abs(verticalOffset);


                if (dy <= toolbarHeight) {

                    float scale = (float) dy / toolbarHeight;
                    float alpha = scale * 255;

                    mFLayout.setBackgroundColor(Color.argb((int) alpha, 255, 64, 129));
                }

//                //第二种
//                mFLayout.setAlpha(percent);
            }
        });

    }
}
