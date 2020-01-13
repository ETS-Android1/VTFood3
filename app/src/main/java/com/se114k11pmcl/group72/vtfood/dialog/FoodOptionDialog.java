package com.se114k11pmcl.group72.vtfood.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.se114k11pmcl.group72.vtfood.Data;
import com.se114k11pmcl.group72.vtfood.R;
import com.se114k11pmcl.group72.vtfood.object.MonAn;
import com.se114k11pmcl.group72.vtfood.screen.DanhSachMonAnActivity;
import com.se114k11pmcl.group72.vtfood.screen.ThemMonAnActivity;

public class FoodOptionDialog extends Dialog {
    MonAn mn;
    DanhSachMonAnActivity ds;
    public FoodOptionDialog(@NonNull Context context, MonAn monAn) {
        super(context);
        this.mn = monAn;
        this.ds = (DanhSachMonAnActivity)context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_option_food);

        TextView txvFoodName = findViewById(R.id.txvFoodName);
        TextView txvChangeFood = findViewById(R.id.txvChangeFood);

        txvFoodName.setText(mn.namef);
        txvChangeFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.getData().idMonAnCanSua = mn.idf;
                ds.startActivityForResult(new Intent(ds, ThemMonAnActivity.class), ds.ID_SUA_MON_AN);
                dismiss();
            }
        });
    }
}
