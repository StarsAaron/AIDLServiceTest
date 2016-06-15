package com.example.aidlservicetest;

import android.app.Application;
import android.util.Log;

/**
 * Created by aaron on 16-6-14.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        Log.i("ServiceMyApplication---","onCreate");
        super.onCreate();
    }
}
