package com.yf.istudy.model;

import android.view.View;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/23.
 */

public class User {
    private  String name;
    private  int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public  void onNameClick(View v){
        Toast.makeText(v.getContext(), "name click ", Toast.LENGTH_SHORT).show();
    }
}
