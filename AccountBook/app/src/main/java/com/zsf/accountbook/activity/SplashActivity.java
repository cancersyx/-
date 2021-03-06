package com.zsf.accountbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.zsf.accountbook.R;
import com.zsf.accountbook.constans.AppConstants;
import com.zsf.accountbook.dao.PasswordDao;
import com.zsf.accountbook.utils.SpUtils;

/**
 * Created by zsf.
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        // 判断是否是第一次开启应用
        boolean isFirstOpen = SpUtils.getBoolean(this, AppConstants.FIRST_OPEN);
        // 如果是第一次启动，则先进入功能引导页
        if (!isFirstOpen) {
            Intent intent = new Intent(this, WelcomeGuideActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        // 如果不是第一次启动app，则正常显示启动屏
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                enterHomeActivity();
            }
        }, 2000);
    }

    private void enterHomeActivity() {
        // TODO: 2018/3/20 ，是否设置了密码，如果设置了就走LoginActivity,否则进主页
        PasswordDao passwordDao = new PasswordDao(SplashActivity.this);
        if (!passwordDao.find().getPassword().isEmpty()) {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, MainNewActivity.class);
            startActivity(intent);
        }
        finish();

    }
}
