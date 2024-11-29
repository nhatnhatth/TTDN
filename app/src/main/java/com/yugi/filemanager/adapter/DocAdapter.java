package com.yugi.filemanager.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yugi.filemanager.R;
import com.yugi.filemanager.base.BaseAdapter;
import com.yugi.filemanager.databinding.ItemDocBinding;
import com.yugi.filemanager.db.Database;
import com.yugi.filemanager.model.ItemFile;
import com.yugi.filemanager.model.ItemmFile;
import com.yugi.filemanager.task.LoadFilesTask;

import java.io.File;
import java.util.List;

public class DocAdapter extends BaseAdapter<ItemmFile> {

    Database database = Database.getInstance();
    public boolean isFavAdapter = false;
    public DocAdapter(List<ItemmFile> mList, Context context) {
        super(mList, context);
    }

    @Override
    protected RecyclerView.ViewHolder viewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_doc, parent, false));
    }

    @Override
    protected void onBindView(RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        ItemDocBinding binding = holder.binding;
        File file = mList.get(position).getFile();
        if (file.getName().toLowerCase().endsWith(".doc")) {
            binding.imageView4.setImageResource(R.drawable.ic_doc);
        } else if (file.getName().toLowerCase().endsWith(".docx")) {
            binding.imageView4.setImageResource(R.drawable.ic_docx);
        } else if (file.getName().toLowerCase().endsWith(".xls")) {
            binding.imageView4.setImageResource(R.drawable.ic_xls);
        } else if (file.getName().toLowerCase().endsWith(".xlsx")) {
            binding.imageView4.setImageResource(R.drawable.ic_xls);
        } else if (file.getName().toLowerCase().endsWith(".txt")) {
            binding.imageView4.setImageResource(R.drawable.ic_txt);
        } else if (file.getName().toLowerCase().endsWith("ppt") || file.getName().toLowerCase().endsWith("pptx")) {
            binding.imageView4.setImageResource(R.drawable.ic_ppt);
        } else if (file.getName().toLowerCase().endsWith(".pdf")) {
            binding.imageView4.setImageResource(R.drawable.ic_pdf);
        } else if (file.getName().toLowerCase().endsWith(".zip") || file.getName().toLowerCase().endsWith(".rar")) {
            binding.imageView4.setImageResource(R.drawable.ic_zips);
        } else if (file.getName().toLowerCase().endsWith(".mp3") || file.getName().toLowerCase().endsWith(".wav")) {
            binding.imageView4.setImageResource(R.drawable.ic_audio);
        }
        if (mList.get(position).isFav) {
            holder.binding.ivFav.setImageTintList(ColorStateList.valueOf(context.getColor(R.color.active)));
        } else {
            holder.binding.ivFav.setImageTintList(ColorStateList.valueOf(context.getColor(R.color.un_active)));
        }
        binding.ivFav.setOnClickListener(view -> {
            mList.get(position).isFav = !mList.get(position).isFav;
            if (mList.get(position).isFav) {
                ItemFile itemFile = new ItemFile();
                itemFile.setPath(file.getPath());
                LoadFilesTask.favFiles.add(mList.get(position));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        database.itemFileDao().insertAll(itemFile);
                    }
                }).start();
            } else {
                LoadFilesTask.favFiles.remove(mList.get(position));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        database.itemFileDao().delete(file.getPath());
                    }
                }).start();
            }
            if(!isFavAdapter){
                notifyItemChanged(position);
            }else {
                notifyItemRemoved(position);
            }
        });
        binding.tvFileName.setText(file.getName());
        binding.tvSize.setText(Formatter.formatFileSize(context, file.length()));
        holder.itemView.setOnClickListener(v -> {
            callback.callback("", mList.get(position));
        });
        holder.itemView.setOnLongClickListener(v -> {
            callback.callback("d", mList.get(position));
            return false;
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemDocBinding binding;

        public ViewHolder(@NonNull ItemDocBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
