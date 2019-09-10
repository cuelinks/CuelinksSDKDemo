package com.cuelinks.demo;

import android.app.Application;

import com.cuelinks.Cuelinks;

/**
 * Created by Yadnyesh on 2019-08-27.
 */
public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Cuelinks.initialize(this);
    }
}
