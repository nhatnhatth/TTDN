package com.yugi.filemanager.db;

import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.yugi.filemanager.model.ItemFile;

@androidx.room.Database(entities = {ItemFile.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract ItemFileDao itemFileDao();

    private static Database instance = null;

    public Database() {
    }

    public static Database getInstance() {
        return instance;
    }

    public static void init(Application application){
        if (instance == null) {
            instance = Room.databaseBuilder(application,
                    Database.class, "database-name").build();
        }
    }

}
