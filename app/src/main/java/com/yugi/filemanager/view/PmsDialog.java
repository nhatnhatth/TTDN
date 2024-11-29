package com.yugi.filemanager.view;

import com.yugi.filemanager.R;
import com.yugi.filemanager.base.BaseDialog;
import com.yugi.filemanager.databinding.DialogPmsBinding;

public class PmsDialog extends BaseDialog<DialogPmsBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.dialog_pms;
    }

    @Override
    protected void initView() {
        setCancelable(false);
    }

    @Override
    protected void addEvent() {
        binding.btnOk.setOnClickListener(v -> {
            callback.callback("");
            dismiss();
        });
    }
}
