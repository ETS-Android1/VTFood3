package com.se114k11pmcl.group72.vtfood.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.se114k11pmcl.group72.vtfood.R;
import com.se114k11pmcl.group72.vtfood.screen.DanhSachMonAnActivity;

public class AddOptionDialog extends Dialog {
    DanhSachMonAnActivity ds;
    public AddOptionDialog(@NonNull Context context) {
        super(context);
        this.ds = (DanhSachMonAnActivity)context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_them);
        TextView txvAddFood = findViewById(R.id.txvAddFood);
        TextView txvAddTable = findViewById(R.id.txvAddTable);
        txvAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ds.moveToAddFood();
                dismiss();
            }
        });
        txvAddTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ds.themBan();
                dismiss();
            }
        });
    }
}
