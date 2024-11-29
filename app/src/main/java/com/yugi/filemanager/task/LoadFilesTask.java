package com.yugi.filemanager.task;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import androidx.room.RoomDatabase;

import com.yugi.filemanager.base.OnActionCallback;
import com.yugi.filemanager.db.Database;
import com.yugi.filemanager.model.ItemFile;
import com.yugi.filemanager.model.ItemmFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LoadFilesTask extends AsyncTask<Void, Void, Void> {
    public static List<ItemmFile> allFiles = new ArrayList<>();
    public static List<ItemmFile> imageFiles = new ArrayList<>();
    public static List<ItemmFile> audioFiles = new ArrayList<>();
    public static List<ItemmFile> videoFiles = new ArrayList<>();
    public static List<ItemmFile> zipFiles = new ArrayList<>();
    public static List<ItemmFile> appFiles = new ArrayList<>();
    public static List<ItemmFile> documentFiles = new ArrayList<>();
    public static List<ItemmFile> downloadFiles = new ArrayList<>();
    public static List<ItemmFile> favFiles = new ArrayList<>();
    public static List<ItemFile> favs = new ArrayList<>();
    Database database = Database.getInstance();
    public OnActionCallback callback;


    @Override
    protected Void doInBackground(Void... voids) {
        // Đường dẫn tới thư mục lưu trữ ngoài của thiết bị
        File storageDir = Environment.getExternalStorageDirectory();

        // Lọc các tệp theo loại (Images, Audio, Videos, Zips, Apps, Documents)
        imageFiles = getFilesFromDirectory(storageDir, Arrays.asList(".jpg", ".jpeg", ".png", ".gif"));
        audioFiles = getFilesFromDirectory(storageDir, Arrays.asList(".mp3", ".wav"));
        videoFiles = getFilesFromDirectory(storageDir, Arrays.asList(".mp4", ".avi", ".mkv"));
        zipFiles = getFilesFromDirectory(storageDir, Arrays.asList(".zip", ".rar"));
        appFiles = getFilesFromDirectory(storageDir, Collections.singletonList(".apk"));
        documentFiles = getFilesFromDirectory(storageDir, Arrays.asList(".pdf", ".docx", ".txt", ".doc", ".ppt", ".pptx", ".xls", ".xlsx"));
        downloadFiles = getFilesFromDirectory(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath()), Collections.singletonList(""));
        allFiles.addAll(imageFiles);
        allFiles.addAll(audioFiles);
        allFiles.addAll(videoFiles);
        allFiles.addAll(zipFiles);
        allFiles.addAll(appFiles);
        allFiles.addAll(documentFiles);
        List<ItemFile> favs = database.itemFileDao().getAll();
        for(ItemmFile item : allFiles){
            for(ItemFile fav: favs){
                if(item.getFile().getPath().equals(fav.getPath())){
                    item.isFav = true;
                    favFiles.add(item);
                    break;
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        callback.callback("");
    }

    // Hàm để lọc các tệp trong một thư mục theo các phần mở rộng
    private List<ItemmFile> getFilesFromDirectory(File dir, List<String> extensions) {
        List<ItemmFile> files = new ArrayList<>();
        if (dir.exists() && dir.isDirectory()) {
            File[] allFiles = dir.listFiles();
            if (allFiles != null) {
                for (File file : allFiles) {
                    // Nếu tệp là một thư mục, tìm thêm trong đó
                    if (file.isDirectory()) {
                        files.addAll(getFilesFromDirectory(file, extensions));
                    } else {
                        // Kiểm tra phần mở rộng của tệp
                        for (String ext : extensions) {
                            if (file.getName().toLowerCase().endsWith(ext)) {
                                files.add(new ItemmFile(file));
                                break;
                            }
                        }
                    }
                }
            }
        }
        return files;
    }

    // Hàm để log các tệp đã tìm thấy
    private void logFiles(String category, List<File> files) {
        Log.d("FileLoader", category + " Files:");
        for (File file : files) {
            Log.d("FileLoader", file.getAbsolutePath());
        }
    }

}

