package com.yf.istudy;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.aplayer.aplayerandroid.APlayerAndroid;
import com.bumptech.glide.Glide;

import java.io.InputStream;

/**
 * Created by Administrator on 2016/11/22.
 */

public class VideoActivity extends Activity implements SurfaceHolder.Callback {
    private SurfaceView sv;
    private APlayerAndroid mAndroid;
    private  String url="http://recordcdn.quklive.com/broadcast/activity/9458019977964845/20160517190000-20160517200010.m3u8";
    private SurfaceHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_video);
        initView();
    }

    private void initView() {
        sv= (SurfaceView) findViewById(R.id.sv);
        holder=sv.getHolder();
        holder.addCallback(this);
        mAndroid=new APlayerAndroid();
        mAndroid.setView(sv);
    }

    public  void  onStart(View view){
        mAndroid.play();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
     //   InputStream is = getResources().openRawResource(R.raw.yw);
        int isOpen=mAndroid.open(url);
        Log.i("111","isOpen:"+isOpen);
        mAndroid.setOnOpenSuccessListener(new APlayerAndroid.OnOpenSuccessListener() {
            @Override
            public void onOpenSuccess() {
                Toast.makeText(VideoActivity.this, "链接成功", Toast.LENGTH_SHORT).show();
            }
        });

        mAndroid.setOnOpenCompleteListener(new APlayerAndroid.OnOpenCompleteListener() {
            @Override
            public void onOpenComplete(boolean b) {
                Toast.makeText(VideoActivity.this, "链接完毕", Toast.LENGTH_SHORT).show();
            }
        });

        mAndroid.setOnPlayStateChangeListener(new APlayerAndroid.OnPlayStateChangeListener() {
            @Override
            public void onPlayStateChange(int i, int i1) {
                Log.i("111","state:"+i);
            }
        });
        Log.i("111","length:"+mAndroid.getDuration());

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
