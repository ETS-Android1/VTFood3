package com.se114k11pmcl.group72.vtfood.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.se114k11pmcl.group72.vtfood.R;
import com.se114k11pmcl.group72.vtfood.screen.DanhSachMonAnActivity;

public class AddTableDialog extends Dialog {
    DanhSachMonAnActivity ds;
    public AddTableDialog(@NonNull Context context) {
        super(context);
        this.ds = (DanhSachMonAnActivity)context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_them_ban);
    }
}
