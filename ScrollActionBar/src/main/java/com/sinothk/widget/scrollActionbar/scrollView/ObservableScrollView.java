package com.sinothk.widget.scrollActionbar.scrollView;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DebugUtils;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sinothk.widget.scrollActionbar.BuildConfig;

/**
 * @ author LiangYT
 * @ create 2018/9/29 9:32
 * @ Describe 自定义带滚动监听的scrollview
 */
public class ObservableScrollView extends ScrollView {
    private ObservableScrollView scrollView;
    /**
     * 接口回调
     */
    private ScrollViewListener scrollViewListener = null;

    /**
     * 构造方法（在这里没用）
     */
    public ObservableScrollView(Context context) {
        super(context);
        scrollView = this;
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        scrollView = this;
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        scrollView = this;
    }

    public interface ScrollViewListener {
        void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy);

    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    /**
     * 重写的onScrollChanged方法监听坐标
     * 这个监听在源码中没有写成回调的样子，
     * 只是写成了方法的样子，由于修饰这个方法的修饰符是protected，
     * （protected只能在本类，子类，同一包中调用），
     * 所以拿到ScrollView对象后在无法activity中调用此方法，
     * 所以只能重写后，子类中自动调用，
     * 所以要想在activity调用，
     * 就要写回调，
     * 上面就是我写的回调
     * 在Android源码中这种写法很多，在很多控件中都有
     */
    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);

        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }

    /**
     * ======================================================================================================
     */
    public void setOnScrollChanging(final ImageView topView, final OnOperateListener onOperateListener) {
        // 获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = topView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                topView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                final int topViewHeight = topView.getHeight();

                scrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
                    @Override
                    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {

                        // Log.i("TAG", "y--->" + y + "    height-->" + height);
                        if (y <= 0) {
                            onOperateListener.onStart();

                            if (BuildConfig.DEBUG) {
                                Log.i("ObservableScrollView", "y=" + y + " <= 0");
                            }

////                          设置文字背景颜色，白色
//                            titleBarLayout.setBackgroundColor(Color.argb((int) 0, 129, 129, 129));//AGB由相关工具获得，或者美工提供
////                          设置文字颜色，黑色
//                            textview.setTextColor(Color.argb((int) 0, 255, 255, 255));
//                            Log.i("111", "y <= 0");

                        } else if (y <= topViewHeight) {


                            float scale = (float) y / topViewHeight;
                            float alpha = (255 * scale);

                            onOperateListener.onChanging(alpha);

                            if (BuildConfig.DEBUG) {
                                Log.i("ObservableScrollView", "y=" + y + ",topViewHeight = " + topViewHeight + ": y <= imageHeight");
                            }

//                            // 只是layout背景透明(仿知乎滑动效果)白色透明
//                            titleBarLayout.setBackgroundColor(Color.argb((int) alpha, 255, 64, 129));
//                            //                          设置文字颜色，黑色，加透明度
//                            textview.setTextColor(Color.argb((int) alpha, 255, 255, 255));
//                            Log.i("111", "y > 0 && y <= imageHeight");

                        } else {
                            onOperateListener.onEnd();

                            if (BuildConfig.DEBUG) {
                                Log.i("ObservableScrollView", "y = " + y + " > imageHeight = " + topViewHeight);
                            }

////							白色不透明
//                            titleBarLayout.setBackgroundColor(Color.argb((int) 255, 255, 64, 129));
//                            //                          设置文字颜色
//                            //黑色
//                            textview.setTextColor(Color.argb((int) 255, 255, 255, 255));
//                            Log.i("111", "else");
                        }
                    }
                });

            }
        });
    }

    public interface OnOperateListener {

        void onStart();

        void onChanging(float alpha);

        void onEnd();
    }
}

