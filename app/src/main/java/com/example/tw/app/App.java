package com.example.tw.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by 18348 on 2016/8/18.
 */
public class App extends Application {
    private static App app;

    public static App getApp() {
        return app;
    }

    public App() {
        app = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Context getContext() {
        return getApp().getApplicationContext();
    }
}
