package com.yugi.filemanager.adapter;

import android.content.Context;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.yugi.filemanager.R;
import com.yugi.filemanager.base.BaseAdapter;
import com.yugi.filemanager.databinding.HomeMenuBinding;
import com.yugi.filemanager.utils.Utils;

import java.util.List;

public class MainAdapter extends BaseAdapter<String> {
    public MainAdapter(List<String> mList, Context context) {
        super(mList, context);
    }

    @Override
    protected RecyclerView.ViewHolder viewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.home_menu, parent, false));
    }

    @Override
    protected void onBindView(RecyclerView.ViewHolder viewHolder1, int position) {
        ViewHolder viewHolder = (ViewHolder) viewHolder1;
        if (mList.get(position).equals("Images")) {
            viewHolder.binding.img.setImageResource(R.drawable.ic_image);
            viewHolder.binding.size.setText(Formatter.formatFileSize(viewHolder.itemView.getContext(), (Utils.EXT_IMAGE_SIZE + Utils.SD_IMAGE_SIZE)));
        } else if (mList.get(position).equals("Audio")) {
            viewHolder.binding.img.setImageResource(R.drawable.ic_music);
            viewHolder.binding.size.setText(Formatter.formatFileSize(viewHolder.itemView.getContext(), (Utils.EXT_AUDIO_SIZE + Utils.SD_AUDIO_SIZE)));
        } else if (mList.get(position).equals("Videos")) {
            viewHolder.binding.img.setImageResource(R.drawable.ic_video);
            viewHolder.binding.size.setText(Formatter.formatFileSize(viewHolder.itemView.getContext(), (Utils.EXT_VIDEO_SIZE + Utils.SD_VIDEO_SIZE)));
        } else if (mList.get(position).equals("Zips")) {
            viewHolder.binding.img.setImageResource(R.drawable.ic_zips);
            viewHolder.binding.size.setText(Formatter.formatFileSize(viewHolder.itemView.getContext(), (Utils.EXT_ZIPS_SIZE + Utils.SD_ZIPS_SIZE)));
        } else if (mList.get(position).equals("Apps")) {
            viewHolder.binding.img.setImageResource(R.drawable.ic_apps);
            viewHolder.binding.size.setText(Formatter.formatFileSize(viewHolder.itemView.getContext(), (Utils.EXT_APPS_SIZE + Utils.SD_APPS_SIZE)));
        } else if (mList.get(position).equals("Document")) {
            viewHolder.binding.img.setImageResource(R.drawable.ic_document);
            viewHolder.binding.size.setText(Formatter.formatFileSize(viewHolder.itemView.getContext(), (Utils.EXT_DOCUMENT_SIZE + Utils.SD_DOCUMENT_SIZE)));
        } else if (mList.get(position).equals("Download")) {
            viewHolder.binding.img.setImageResource(R.drawable.ic_download);
            viewHolder.binding.size.setText(Formatter.formatFileSize(viewHolder.itemView.getContext(), (Utils.EXT_DOWNLOAD_SIZE)));
        } else {
            viewHolder.binding.img.setImageResource(R.drawable.ic_app_favourite);
            viewHolder.binding.size.setText("");
        }
        viewHolder.binding.txt.setText(mList.get(position));
        viewHolder.itemView.setOnClickListener(v -> {
            callback.callback(String.valueOf(position));
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        HomeMenuBinding binding;

        public ViewHolder(@NonNull HomeMenuBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
