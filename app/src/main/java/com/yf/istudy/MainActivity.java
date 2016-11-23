package com.yf.istudy;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.yf.istudy.adapter.MainAdapter;
import com.yf.istudy.model.ItNews;
import com.yf.istudy.utils.Constant;
import com.yf.istudy.utils.JsoupUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private ListView lv_lt;
    private List<ItNews> mNewses = new ArrayList<>();
    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }
    private void initData() {
        new GetNewsTask().execute();
    }
    private void initView() {
        lv_lt = (ListView) findViewById(R.id.lv_lt);
        mAdapter=new MainAdapter(mNewses,this);
        lv_lt.setAdapter(mAdapter);
    }

    class GetNewsTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                String url = Constant.IQIYI+Constant.DIANYING;
                Log.i("111","url:"+url);
                Document doc = Jsoup.connect(url)
                        .timeout(1000*10)
                        .get();
                mNewses = JsoupUtils.parseIqIyIList(doc);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
