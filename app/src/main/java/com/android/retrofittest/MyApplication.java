package com.android.retrofittest;

import android.app.Application;

public class MyApplication extends Application {

    private MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
