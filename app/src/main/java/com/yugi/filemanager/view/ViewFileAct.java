package com.yugi.filemanager.view;

import android.os.Bundle;

import com.yugi.filemanager.R;
import com.yugi.filemanager.base.BaseActivity;
import com.yugi.filemanager.databinding.ActivityViewFileBinding;
import com.yugi.filemanager.fragment.DocumentFragment;
import com.yugi.filemanager.fragment.FavouriteFragment;

public class ViewFileAct extends BaseActivity<ActivityViewFileBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_view_file;
    }

    @Override
    protected void initView() {
        int num = getIntent().getIntExtra("pos", 0);
        switch (num) {
            case 0:
                binding.tittle.setText("Images");
                getSupportFragmentManager().beginTransaction().replace(binding.container.getId(), new com.yugi.filemanager.view.ImageFragment()).commit();
                break;
            case 1:
                binding.tittle.setText("Audios");
                getSupportFragmentManager().beginTransaction().replace(binding.container.getId(), new AudioFragment()).commit();
                break;
            case 2:
                binding.tittle.setText("Video");
                getSupportFragmentManager().beginTransaction().replace(binding.container.getId(), new VideoFragment()).commit();
                break;
            case 3:
                binding.tittle.setText("Zips");
                getSupportFragmentManager().beginTransaction().replace(binding.container.getId(), new ZipFragment()).commit();
                break;
            case 4:
                binding.tittle.setText("Apps");
                getSupportFragmentManager().beginTransaction().replace(binding.container.getId(), new AppsFragment()).commit();
                break;
            case 5:
                binding.tittle.setText("Documents");
                getSupportFragmentManager().beginTransaction().replace(binding.container.getId(), new DocFragment()).commit();
                break;
            case 6:
                binding.tittle.setText("Favorites");
                getSupportFragmentManager().beginTransaction().replace(binding.container.getId(), new FavouriteFragment()).commit();
                break;
            case 7:
                break;
        }
    }

    @Override
    protected void addEvent() {

    }
}
