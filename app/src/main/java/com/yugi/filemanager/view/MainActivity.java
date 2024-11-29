package com.yugi.filemanager.view;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.yugi.filemanager.R;
import com.yugi.filemanager.adapter.MainAdapter;
import com.yugi.filemanager.base.BaseActivity;
import com.yugi.filemanager.base.OnActionCallback;
import com.yugi.filemanager.databinding.ActivityHomeBinding;
import com.yugi.filemanager.task.LoadFilesTask;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityHomeBinding> {

    List<String> list = Arrays.asList("Images", "Audio", "Videos", "Zips", "Apps", "Download", "Favorites");

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        binding.rv.setVisibility(View.GONE);
        LoadFilesTask task = new LoadFilesTask();
        task.callback = new OnActionCallback() {
            @Override
            public void callback(String key, Object... data) {
                binding.prgress.setVisibility(View.GONE);
                binding.rv.setVisibility(View.VISIBLE);
            }
        };
        task.execute();
        MainAdapter adapter = new MainAdapter(list, this);
        binding.rv.setLayoutManager(new GridLayoutManager(this, 4));
        adapter.setCallback(new OnActionCallback() {
            @Override
            public void callback(String key, Object... data) {
                int num = Integer.parseInt(key);
                Intent intent = new Intent(MainActivity.this, ViewFileAct.class);
                intent.putExtra("pos", num);
                startActivity(intent);
            }
        });
        binding.rv.setAdapter(adapter);
    }

    @Override
    protected void addEvent() {

    }
}
