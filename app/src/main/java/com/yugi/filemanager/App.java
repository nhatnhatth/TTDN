package com.yugi.filemanager;

import android.app.Application;

import com.yugi.filemanager.db.Database;
import com.yugi.filemanager.repo.AppRepo;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Database.init(this);
        AppRepo.application = this;
    }
}
