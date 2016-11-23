package com.yf.istudy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yf.istudy.MainActivity;
import com.yf.istudy.model.ItNews;

import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */

public class MainAdapter extends BaseAdapter {
    private final List<ItNews> newses;
    private final Context context;

    public MainAdapter(List<ItNews> newses, Context context) {
        this.newses=newses;
        this.context=context;
    }

    @Override
    public int getCount() {
        return newses.size();
    }

    @Override
    public Object getItem(int position) {
        return newses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){

        }
        return convertView;
    }
}
