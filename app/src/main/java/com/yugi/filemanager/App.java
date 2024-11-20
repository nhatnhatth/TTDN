package com.yugi.filemanager;

import android.app.Application;

import com.yugi.filemanager.db.Database;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Database.init(this);
    }
}
