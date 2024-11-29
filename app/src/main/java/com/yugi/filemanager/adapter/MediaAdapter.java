package com.yugi.filemanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yugi.filemanager.R;
import com.yugi.filemanager.base.BaseAdapter;
import com.yugi.filemanager.databinding.ItemMediaBinding;
import com.yugi.filemanager.model.ItemmFile;
import com.yugi.filemanager.task.LoadFilesTask;

import java.io.File;
import java.util.List;

public class MediaAdapter extends BaseAdapter<ItemmFile> {
    public MediaAdapter(List<ItemmFile> mList, Context context) {
        super(mList, context);
    }

    @Override
    protected RecyclerView.ViewHolder viewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_media, parent, false));
    }

    @Override
    protected void onBindView(RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        Glide.with(viewHolder.itemView.getContext()).load(mList.get(position).getFile()).into(holder.binding.img);
        if (LoadFilesTask.videoFiles.contains(mList.get(position))) {
            holder.binding.imgAct.setVisibility(View.VISIBLE);
        } else {
            holder.binding.imgAct.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(v -> {
            callback.callback("", mList.get(position));
        });
        holder.itemView.setOnLongClickListener(v -> {
            callback.callback("d", mList.get(position));
            return false;
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemMediaBinding binding;

        public ViewHolder(@NonNull ItemMediaBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
