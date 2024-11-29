package com.yugi.filemanager.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.yugi.filemanager.model.ItemFile;

import java.util.List;

@Dao
public interface ItemFileDao {
    @Query("SELECT * FROM  item_file")
    List<ItemFile> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(ItemFile... item);

    @Delete
    void delete(ItemFile item);

    @Query("DELETE FROM  item_file WHERE path = :pathData ")
    void delete(String pathData);
}
