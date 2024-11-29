package com.yugi.filemanager.model;

import java.io.File;

public class ItemmFile {
    File file = null;
    public boolean isFav = false;

    public ItemmFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

}
