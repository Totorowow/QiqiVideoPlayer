package com.woodpecker.qiqivideoplayer.newPlayer.activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.frank.ffmpeg.activity.FilterActivity;
import com.frank.ffmpeg.activity.VideoHandleActivity;
import com.woodpecker.qiqivideoplayer.databinding.ActivityMainBinding;

import com.woodpecker.qiqivideoplayer.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        initListener();
        mainBinding.tvTitle.setText(getResources().getString(R.string.app_name));
        //setLanguage("en");

    }


    private void initListener() {

        mainBinding.squid.setOnClickListener(this);
        mainBinding.squid1.setOnClickListener(this);
        mainBinding.squid2.setOnClickListener(this);
        mainBinding.squid3.setOnClickListener(this);
        mainBinding.squid4.setOnClickListener(this);
        mainBinding.squid5.setOnClickListener(this);
        mainBinding.squid6.setOnClickListener(this);
        mainBinding.squid7.setOnClickListener(this);
        mainBinding.squid8.setOnClickListener(this);
        mainBinding.squid9.setOnClickListener(this);
        mainBinding.squid10.setOnClickListener(this);
        mainBinding.squid11.setOnClickListener(this);
        mainBinding.squid12.setOnClickListener(this);
        mainBinding.squid13.setOnClickListener(this);
        mainBinding.squid14.setOnClickListener(this);
        mainBinding.squid15.setOnClickListener(this);
       mainBinding.squid16.setOnClickListener(this);
        mainBinding.squid17.setOnClickListener(this);
        mainBinding.squid18.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == mainBinding.squid){
            startSpecifiedActivity(NormalVideoActivity.class);
        } else if (v == mainBinding.squid1){
            startSpecifiedActivity(TestFullActivity.class);
        } else if (v == mainBinding.squid2){
            startSpecifiedActivity(MultipleActivity.class);
        } else if (v == mainBinding.squid3){
            startSpecifiedActivity(FloatWindowActivity.class);
        } else if (v == mainBinding.squid4){
            startSpecifiedActivity(PipListActivity.class);
        }   else if (v == mainBinding.squid5){
            startSpecifiedActivity(TinyScreenActivity.class);
        } else if (v == mainBinding.squid6){

            Intent intent = new Intent(this, TestListActivity.class);
            intent.putExtra("type",0);
            startActivity(intent);
        } else if (v == mainBinding.squid7){
            Intent intent = new Intent(this, TestListActivity.class);
            intent.putExtra("type",1);
            startActivity(intent);
        } else if (v == mainBinding.squid8){
            Intent intent = new Intent(this, TestListActivity.class);
            intent.putExtra("type",2);
            startActivity(intent);
        } else if (v == mainBinding.squid9){
            Intent intent = new Intent(this, TestListActivity.class);
            intent.putExtra("type",3);
            startActivity(intent);
        } else if (v == mainBinding.squid10){
            Intent intent = new Intent(this, TestListActivity.class);
            intent.putExtra("type",4);
            startActivity(intent);
        } else if (v == mainBinding.squid11){
            Intent intent = new Intent(this, TestListActivity.class);
            intent.putExtra("type",5);
            startActivity(intent);
        } else if (v == mainBinding.squid12){
            startSpecifiedActivity(BulletScreenActivity.class);
        } else if (v == mainBinding.squid13){
            startSpecifiedActivity(AdActivity.class);
            startActivity(new Intent(this, AdActivity.class));
        } else if (v == mainBinding.squid14){
            startSpecifiedActivity(ContinuousVideoActivity.class);
        } else if (v == mainBinding.squid15){
            startSpecifiedActivity(ClarityActivity.class);
        } else if (v == mainBinding.squid16){
            startSpecifiedActivity(VideoHandleActivity.class);
        }else if (v == mainBinding.squid17){
            startSpecifiedActivity(FilterActivity.class);
        }else if (v == mainBinding.squid18){
            startSpecifiedActivity(TestSurfaceActivity.class);
        }
    }

    private void startSpecifiedActivity(Class<?> specifiedClass){
        startActivity(new Intent(this, specifiedClass));

    }

    private void setLanguage(String language){
        Resources resources = this.getResources();
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        if (language == null){
            configuration.setLocale(Locale.getDefault());
        }else {
            switch (language) {
                case "en":
                    configuration.setLocale(Locale.ENGLISH);
                    break;
                case "zh-rCN":
                    configuration.setLocale(Locale.SIMPLIFIED_CHINESE);
                    break;
                case "es":
                    configuration.setLocale(new Locale("es"));
                    break;
            }
        }
        resources.updateConfiguration(configuration,displayMetrics);
    }


}
