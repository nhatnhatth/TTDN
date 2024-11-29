package com.yugi.filemanager.view;

import com.yugi.filemanager.R;
import com.yugi.filemanager.base.BaseDialog;
import com.yugi.filemanager.databinding.DialogDeleteBinding;
import com.yugi.filemanager.databinding.DialogPmsBinding;

public class DeleteDialog extends BaseDialog<DialogDeleteBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.dialog_delete;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void addEvent() {
        binding.btnDelete.setOnClickListener(v -> {
            callback.callback("");
            dismiss();
        });
        binding.btnCancel.setOnClickListener(v -> {
            dismiss();
        });
    }
}
