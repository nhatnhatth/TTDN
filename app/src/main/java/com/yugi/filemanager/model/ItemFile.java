package com.yugi.filemanager.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "item_file")
public class ItemFile {
    @PrimaryKey(autoGenerate = true)
    private int id;


    @ColumnInfo(name = "path")
    private String path = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
