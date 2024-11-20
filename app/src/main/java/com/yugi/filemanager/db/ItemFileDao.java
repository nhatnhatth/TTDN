package com.yugi.filemanager.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.yugi.filemanager.model.ItemFile;

import java.util.List;

@Dao
public interface ItemFileDao {
    @Query("SELECT * FROM  item_file")
    List<ItemFile> getAll();

    @Insert
    void insertAll(ItemFile... item);

    @Delete
    void delete(ItemFile item);
}
