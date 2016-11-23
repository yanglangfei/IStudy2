package com.yf.istudy;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/11/23.
 */

public class TikActivity extends Activity {
    private TextView tok;
    private ImageView iv;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==100)
            tok.setText("I am  net  data");
            if(msg.what==200)
                iv.setImageResource(R.drawable.page);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_tik);
        tok= (TextView) findViewById(R.id.tok);
        iv= (ImageView) findViewById(R.id.iv);
        mHandler.sendEmptyMessageDelayed(200,1000*20);
        mHandler.sendEmptyMessageDelayed(100,1000*10);
    }
}
