package com.woodpecker.qiqivideoplayer.newPlayer.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.woodpecker.qiqivideoplayer.newPlayer.ad.AdActivity;
import com.woodpecker.qiqivideoplayer.newPlayer.clarity.ClarityActivity;
import com.woodpecker.qiqivideoplayer.newPlayer.danmu.BulletScreenActivity;
import com.woodpecker.qiqivideoplayer.newPlayer.list.ContinuousVideoActivity;
import com.woodpecker.qiqivideoplayer.newPlayer.list.TestListActivity;
import com.woodpecker.qiqivideoplayer.newPlayer.pip.PipActivity;
import com.woodpecker.qiqivideoplayer.newPlayer.pip.PipListActivity;
import com.woodpecker.qiqivideoplayer.newPlayer.surface.TestSurfaceActivity;
import com.woodpecker.qiqivideoplayer.newPlayer.tiny.TestFullActivity;
import com.woodpecker.qiqivideoplayer.newPlayer.tiny.TinyScreenActivity;

import com.woodpecker.qiqivideoplayer.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView mTvTitle;
    private TextView mTv31;
    private TextView mTv32;
    private TextView mTv33;
    private TextView mTv41;
    private TextView mTv42;
    private TextView mTv43;
    private TextView mTv61;
    private TextView mTv62;
    private TextView mTv63;
    private TextView mTv64;
    private TextView mTv65;
    private TextView mTv66;
    private TextView mTv71;
    private TextView mTv81;
    private TextView mTv101;
    private TextView mTv111;
    private TextView mTv131;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        initFindViewById();
        initListener();
        mTvTitle.setText(getResources().getString(R.string.app_name));
        setTitle(getResources().getString(R.string.app_name));
    }


    private void initFindViewById() {
        mToolbar = findViewById(R.id.toolbar);
        mTvTitle = findViewById(R.id.tv_title);
        mTv31 = findViewById(R.id.tv_3_1);
        mTv32 = findViewById(R.id.tv_3_2);
        mTv33 = findViewById(R.id.tv_3_3);
        mTv41 = findViewById(R.id.tv_4_1);
        mTv42 = findViewById(R.id.tv_4_2);
        mTv43 = findViewById(R.id.tv_4_3);
        mTv61 = findViewById(R.id.tv_6_1);
        mTv62 = findViewById(R.id.tv_6_2);
        mTv63 = findViewById(R.id.tv_6_3);
        mTv64 = findViewById(R.id.tv_6_4);
        mTv65 = findViewById(R.id.tv_6_5);
        mTv66 = findViewById(R.id.tv_6_6);
        mTv71 = findViewById(R.id.tv_7_1);
        mTv81 = findViewById(R.id.tv_8_1);
        mTv101 = findViewById(R.id.tv_10_1);
        mTv111 = findViewById(R.id.tv_11_1);
        mTv131 = findViewById(R.id.tv_13_1);
    }

    private void initListener() {

        mTv31.setOnClickListener(this);
        mTv32.setOnClickListener(this);
        mTv33.setOnClickListener(this);
        mTv41.setOnClickListener(this);
        mTv42.setOnClickListener(this);
        mTv43.setOnClickListener(this);
        mTv61.setOnClickListener(this);
        mTv62.setOnClickListener(this);
        mTv63.setOnClickListener(this);
        mTv64.setOnClickListener(this);
        mTv65.setOnClickListener(this);
        mTv66.setOnClickListener(this);
        mTv71.setOnClickListener(this);
        mTv81.setOnClickListener(this);
        mTv101.setOnClickListener(this);
        mTv111.setOnClickListener(this);
        mTv131.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mTv31){
            startActivity(new Intent(this, CustomVideoActivity.class));
        } else if (v == mTv32){
            startActivity(new Intent(this, TestFullActivity.class));
        } else if (v == mTv33){
            startActivity(new Intent(this,MultipleActivity.class));
        } else if (v == mTv41){
            startActivity(new Intent(this, PipActivity.class));
        } else if (v == mTv42){
            startActivity(new Intent(this, PipListActivity.class));
        }   else if (v == mTv43){
            startActivity(new Intent(this, TinyScreenActivity.class));
        } else if (v == mTv61){
            Intent intent = new Intent(this, TestListActivity.class);
            intent.putExtra("type",0);
            startActivity(intent);
        } else if (v == mTv62){
            Intent intent = new Intent(this, TestListActivity.class);
            intent.putExtra("type",1);
            startActivity(intent);
        } else if (v == mTv63){
            Intent intent = new Intent(this, TestListActivity.class);
            intent.putExtra("type",2);
            startActivity(intent);
        } else if (v == mTv64){
            Intent intent = new Intent(this, TestListActivity.class);
            intent.putExtra("type",3);
            startActivity(intent);
        } else if (v == mTv65){
            Intent intent = new Intent(this, TestListActivity.class);
            intent.putExtra("type",4);
            startActivity(intent);
        } else if (v == mTv66){
            Intent intent = new Intent(this, TestListActivity.class);
            intent.putExtra("type",5);
            startActivity(intent);
        } else if (v == mTv71){
            startActivity(new Intent(this, BulletScreenActivity.class));
        } else if (v == mTv81){
            startActivity(new Intent(this, AdActivity.class));
        } else if (v == mTv101){
            startActivity(new Intent(this, ContinuousVideoActivity.class));
        } else if (v == mTv111){
            startActivity(new Intent(this, ClarityActivity.class));
        } else if (v == mTv131){
            startActivity(new Intent(this, TestSurfaceActivity.class));
        }
    }

}
