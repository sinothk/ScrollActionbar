package com.sinothk.widget.scrollActionBar.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ScrollActionbarDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_actionbar_demo);
    }

    public void gotoStyle1(View view) {
        startActivity(new Intent(this, ScrollActionbarStyle1Activity.class));
    }

    public void gotoStyle2(View view) {
        startActivity(new Intent(this, ScrollActionbarStyle2Activity.class));
    }
}
