package com.yf.istudy;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.aplayer.aplayerandroid.APlayerAndroid;
import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */

public class VideoActivity extends Activity implements SurfaceHolder.Callback {
    private SurfaceView sv;
    private ImageView capImg;
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
        capImg= (ImageView) findViewById(R.id.capImg);
        holder=sv.getHolder();
        holder.addCallback(this);
        //创建播放器对象
        mAndroid=new APlayerAndroid();
        //是否使用系统播放器   默认不使用
        mAndroid.useSystemPlayer(false);
        //创建VR 视图   需要设置视图大小和位置
        View vr=mAndroid.createVRView(this);
        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(500,250);
        vr.setLayoutParams(lp);
        //是否打开后自动播放    0  是    1  否  默认   不自动播放
        mAndroid.setConfig(APlayerAndroid.CONFIGID.AUTO_PLAY,"0");

        //刷新页面
        mAndroid.setConfig(APlayerAndroid.CONFIGID.UPDATEWINDOW,"1000");
        //设置视频的自定义宽高比   纵横比设置只对软解有用  对于硬解和系统播放器无效   只能调整SurFaceView 宽高
        mAndroid.setConfig(APlayerAndroid.CONFIGID.ASPECT_RATIO_CUSTOM,"6;5");
        //设置硬件解码状态   0  关闭    1  打开   open 之前调用    非VR 状态有效
        mAndroid.setConfig(APlayerAndroid.CONFIGID.HW_DECODER_USE,"1");
        //是否开启硬件解码侦测  默认开启    open前设置有效   0   不开启   1  开启
        mAndroid.setConfig(APlayerAndroid.CONFIGID.HW_DECODER_DETEC,"0");
        //设置当前音轨索引  索引从0开始
        mAndroid.setConfig(APlayerAndroid.CONFIGID.AUDIO_TRACK_CURRENT,"0");
        //设置字幕加载的文件名
        mAndroid.setConfig(APlayerAndroid.CONFIGID.SUBTITLE_FILE_NAME,"/SUBTITLE.str");
        //设置字幕状态   0  隐藏    1  显示
        mAndroid.setConfig(APlayerAndroid.CONFIGID.SUBTITLE_SHOW,"0");
        //设置当前选择字幕索引
        mAndroid.setConfig(APlayerAndroid.CONFIGID.SUBTITLE_CURLANG,"0");
        //设置http请求头中带cookie的字段
        mAndroid.setConfig(APlayerAndroid.CONFIGID.HTTP_COOKIE,"");
        //设置请求头中带referer 字段
        mAndroid.setConfig(APlayerAndroid.CONFIGID.HTTP_REFERER,"");
        //设置http自定义表头字段列表
        mAndroid.setConfig(APlayerAndroid.CONFIGID.HTTP_CUSTOM_HEADERS,"");




         //返回播放结束的结果   0  视频播放完成     1   用户退出播放    其他 异常退出
        String config1 = mAndroid.getConfig(APlayerAndroid.CONFIGID.PLAYRESULT);
        //返回当前视频缓冲位置    默认毫秒
        String config2 = mAndroid.getConfig(APlayerAndroid.CONFIGID.READPOSITION);
        //返回视频自然宽高比  格式 ：“3；4”
        String config3=mAndroid.getConfig(APlayerAndroid.CONFIGID.ASPECT_RATIO_NATIVE);
        //返回系统是否支持当前媒体硬解码  0  不支持    1   支持
        String config4=mAndroid.getConfig(APlayerAndroid.CONFIGID.HW_DECODER_ENABLE);
        //获取音轨列表
        String config5=mAndroid.getConfig(APlayerAndroid.CONFIGID.AUDIO_TRACK_LIST);
        //获取字幕加载功能是否可以   0  不可用   1  可用
        String config6=mAndroid.getConfig(APlayerAndroid.CONFIGID.SUBTITLE_USABLE);
        //获取支持的字幕格式列表
        String config7=mAndroid.getConfig(APlayerAndroid.CONFIGID.SUBTITLE_EXT_NAME);
        //获取当前加载字幕的可用语言列表
        String config8=mAndroid.getConfig(APlayerAndroid.CONFIGID.SUBTITLE_LANGLIST);







        //打开一个视频文件  可以是本地文件  也可以是网络文件  调用成功返回0
        int isOpen=mAndroid.open(url);
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

        mAndroid.setOnBufferListener(new APlayerAndroid.OnBufferListener() {
            @Override
            public void onBuffer(int i) {
                Log.i("111","buffer change:"+i);
            }
        });

    }

    public  void  onCap(View view){
        //获取2分钟出的视频截图
        APlayerAndroid.MediaInfo img = mAndroid.parseThumbnail(url, 1000 * 60 * 20, 300, 150);
        Bitmap bm= BitmapFactory.decodeByteArray(img.bitMap,0,img.bitMap.length);
        capImg.setImageBitmap(bm);
    }

    public  void  onPause(View view){
        //暂停  调用成功返回0
        if(mAndroid!=null)
        mAndroid.pause();

        //获取视频播放状态
        int state=mAndroid.getState();
        switch (state){
            case APlayerAndroid.PlayerState.APLAYER_READ:
                break;
            case APlayerAndroid.PlayerState.APLAYER_OPENING:
                break;
            case APlayerAndroid.PlayerState.APLAYER_PAUSING:
                break;
            case APlayerAndroid.PlayerState.APLAYER_PAUSED:
                break;
            case APlayerAndroid.PlayerState.APLAYER_PLAYING:
                break;
            case APlayerAndroid.PlayerState.APLAYER_PLAY:
                break;
            case APlayerAndroid.PlayerState.APLAYER_CLOSEING:
                break;
        }



    }

    public  void  onStart(View view){
        //播放   返回0  播放成功   打开后不会自动播放 需要调用该方法
        int result=mAndroid.play();
        Log.i("111","res:"+result);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mAndroid.setView(holder.getSurface());
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mAndroid!=null){
            //关闭播放器  下次打开会更快  成功返回0
            mAndroid.close();
            //释放资源
            mAndroid.destroy();
            mAndroid=null;
        }
    }
}
