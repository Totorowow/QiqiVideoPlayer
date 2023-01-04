package com.woodpecker.qiqivideoplayer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.woodpecker.qiqivideoplayer.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class CoreDependencyActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTv1;
    private TextView mTv12;
    private TextView mTv2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_player);

        mTv1 = findViewById(R.id.tv_1);
        mTv12 = findViewById(R.id.tv_1_2);
        mTv2 = findViewById(R.id.tv_2);

        mTv1.setOnClickListener(this);
        mTv12.setOnClickListener(this);
        mTv2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_1:
                startActivity(MediaActivity.class);
                break;
            case R.id.tv_1_2:
                startActivity(MediaActivity2.class);
                break;


        }
    }

    private void startActivity(Class c){
        startActivity(new Intent(this,c));
    }

}
