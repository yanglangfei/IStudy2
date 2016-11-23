package com.yf.istudy.utils;

import android.util.Log;

import com.yf.istudy.model.ItNews;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */

public class JsoupUtils {
    public static List<ItNews> parseIqIyIList(Document doc) {
        Log.i("111","doc:"+doc.body());
        Element ul = doc.getElementsByClass("site-piclist").first();
        Elements lis = ul.getElementsByTag("li");
        for (int i = 0; i < lis.size(); i++) {
            Element li = lis.get(i);
            Element desc = li.getElementsByClass("img").first();
            Log.i("111","desc:"+desc.attr("src"));
        }

        return null;
    }
}
