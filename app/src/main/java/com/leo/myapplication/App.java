package com.leo.myapplication;

import android.app.Application;
import android.content.Context;

/**
 * Created by Leo on 2018/8/28.
 */

public class App extends Application {
    private static App context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
