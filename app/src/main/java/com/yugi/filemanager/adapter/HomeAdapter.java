package com.yugi.filemanager.adapter;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yugi.filemanager.MainActivity;
import com.yugi.filemanager.R;
import com.yugi.filemanager.databinding.RecentItemBinding;
import com.yugi.filemanager.fragment.CleanerFragment;
import com.yugi.filemanager.fragment.ImageFragment;
import com.yugi.filemanager.fragment.SearchFragment;
import com.yugi.filemanager.interfaces.HomeAppClickListener;
import com.yugi.filemanager.interfaces.StorageClickListener;
import com.yugi.filemanager.databinding.CleanerItemBinding;
import com.yugi.filemanager.databinding.RvItemBinding;
import com.yugi.filemanager.databinding.SearchbarItemBinding;
import com.yugi.filemanager.databinding.StorageSpaceItemBinding;
import com.yugi.filemanager.utils.Utils;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.Executors;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    HomeAppAdapter homeAppAdapter;
    String[] apps;
    Activity mainActivity;
    StorageClickListener storageClickListener;
    HomeAppClickListener homeAppClickListener;

    public HomeAdapter(Activity mainActivity, String[] apps, StorageClickListener storageClickListener, HomeAppClickListener homeAppClickListener) {
        this.apps = apps;
        this.mainActivity = mainActivity;
        this.homeAppClickListener = homeAppClickListener;
        homeAppAdapter = new HomeAppAdapter(mainActivity, apps, homeAppClickListener);
        this.storageClickListener = storageClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainMenuViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.rv_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MainMenuViewHolder holder1 = (MainMenuViewHolder) holder;
        holder1.binding.rv.setLayoutManager(new GridLayoutManager(holder1.itemView.getContext(), 4));
        holder1.binding.rv.setAdapter(homeAppAdapter);
    }

    ArrayList<File> img(File file) {
        ArrayList<File> fileArrayList = new ArrayList<>();
        File[] files = file.listFiles();
        if (files != null) {
            for (File singleFile : files) {
                if (singleFile.isDirectory() && !singleFile.isHidden()) {
                    fileArrayList.addAll(img(singleFile));
                } else {
                    if (!singleFile.isHidden() && singleFile.getName().toLowerCase().endsWith(".jpg") || singleFile.getName().toLowerCase().endsWith(".jpeg") || singleFile.getName().toLowerCase().endsWith(".png")) {
                        fileArrayList.add(singleFile);
                    }
                }
            }
        }
        return fileArrayList;
    }

    ArrayList<File> shortFile(ArrayList<File> files) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            files.sort(Comparator.comparing(File::lastModified).reversed());
        }
        return files;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public void notifyAppData() {
        homeAppAdapter.notifyApps();
    }

    class MainMenuViewHolder extends RecyclerView.ViewHolder {

        RvItemBinding binding;

        public MainMenuViewHolder(@NonNull RvItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

    }
}
