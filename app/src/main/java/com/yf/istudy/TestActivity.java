package com.yf.istudy;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.yf.istudy.databinding.UiTestBinding;
import com.yf.istudy.model.User;

/**
 * Created by Administrator on 2016/11/23.
 */
public class TestActivity extends Activity {
    private UiTestBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this,R.layout.ui_test);
        User user=new User();
        user.setName("张三");
        user.setAge(15);
        mBinding.setUser(user);

    }
}
