package com.yugi.filemanager.view;

import android.content.Intent;
import android.net.Uri;

import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.yugi.filemanager.R;
import com.yugi.filemanager.adapter.DocAdapter;
import com.yugi.filemanager.adapter.MediaAdapter;
import com.yugi.filemanager.base.BaseActivity;
import com.yugi.filemanager.base.BaseFragment;
import com.yugi.filemanager.base.OnActionCallback;
import com.yugi.filemanager.databinding.FragmentMediaBinding;
import com.yugi.filemanager.db.Database;
import com.yugi.filemanager.model.ItemFile;
import com.yugi.filemanager.model.ItemmFile;
import com.yugi.filemanager.task.LoadFilesTask;

import java.util.Objects;

public class ImageFragment extends BaseFragment<FragmentMediaBinding> {

    MediaAdapter adapter = null;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_media;
    }

    @Override
    protected void initView() {
        adapter = new MediaAdapter(LoadFilesTask.imageFiles, requireActivity());
        adapter.setCallback(new OnActionCallback() {
            @Override
            public void callback(String key, Object... data) {
                ItemmFile item = (ItemmFile) data[0];
                if(Objects.equals(key, "d")){
                    deleteF(item);
                    return;
                }
                Uri uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", item.getFile());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(uri, "image/*");  // MIME type for image files
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                requireActivity().startActivity(intent);
            }
        });
        binding.rcv.setLayoutManager(new GridLayoutManager(requireActivity(), 3));
        binding.rcv.setAdapter(adapter);
    }

    @Override
    protected void addEvent() {

    }

    private void deleteF(ItemmFile item) {
        DeleteDialog dialog = new DeleteDialog();
        dialog.setCallback(new OnActionCallback() {
            @Override
            public void callback(String key, Object... data) {
                item.getFile().delete();
                LoadFilesTask.allFiles.remove(item);
                LoadFilesTask.favFiles.remove(item);
                adapter.notifyItemRemoved(LoadFilesTask.imageFiles.indexOf(item));
                LoadFilesTask.imageFiles.remove(item);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ItemFile itemFile = new ItemFile();
                        itemFile.setPath(item.getFile().getPath());
                        Database.getInstance().itemFileDao().delete(itemFile);
                    }
                }).start();
            }
        });
        dialog.showDialog((BaseActivity) requireActivity());
    }
}
