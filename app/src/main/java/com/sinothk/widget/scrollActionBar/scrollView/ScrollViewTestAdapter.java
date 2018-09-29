package com.sinothk.widget.scrollActionBar.scrollView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinothk.widget.scrollActionBar.demo.R;

import java.util.ArrayList;

/**
 * @ author LiangYT
 * @ create 2018/9/29 11:42
 * @ Describe
 */
class ScrollViewTestAdapter extends RecyclerView.Adapter {

    ArrayList<String> data;
    ScrollViewDemoRecycleViewMainActivity curActivity;

    ScrollViewTestAdapter(ScrollViewDemoRecycleViewMainActivity curActivity, ArrayList<String> data) {
        this.data = data;
        this.curActivity = curActivity;
    }

    @Override
    public int getItemCount() {
        return this.data == null ? 0 : this.data.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(curActivity).inflate(R.layout.scroll_view_demo_activity_main_recycle_view_item, parent, false);
        return new TestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TestHolder testHolder = (ScrollViewTestAdapter.TestHolder) holder;

        testHolder.textView.setText(data.get(position));
    }

    class TestHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public TestHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
