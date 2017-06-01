package com.example.recyclerview_master.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.recyclerview_master.R;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_to_single).setOnClickListener(this);
        findViewById(R.id.btn_to_tree).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_to_single:
                startActivity(new Intent(MainActivity.this, SingleRecyclerView.class));
                break;
            case R.id.btn_to_tree:
                startActivity(new Intent(MainActivity.this, TreeRecyclerView.class));
                break;
        }
    }
}
