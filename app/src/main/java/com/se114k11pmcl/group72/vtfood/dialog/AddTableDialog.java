package com.se114k11pmcl.group72.vtfood.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.se114k11pmcl.group72.vtfood.R;
import com.se114k11pmcl.group72.vtfood.api.RunSQL;
import com.se114k11pmcl.group72.vtfood.screen.DanhSachMonAnActivity;

public class AddTableDialog extends Dialog {
    DanhSachMonAnActivity ds;
    EditText edtAdd_TableName;
    public AddTableDialog(@NonNull Context context) {
        super(context);
        this.ds = (DanhSachMonAnActivity)context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_them_ban);

        TextView txvAdd_Table = findViewById(R.id.txvAdd_Table);
        edtAdd_TableName = findViewById(R.id.edtAdd_TableName);

        txvAdd_Table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "" + edtAdd_TableName.getText().toString();
                if(name.length()==0){
                    Toast.makeText(ds,"Thiếu tên bàn",Toast.LENGTH_SHORT).show();
                    return;
                }
                String sql = "INSERT INTO `table` (`idt`, `namet`) VALUES (NULL, '" +
                        name +
                        "')";
                new RunSQL(sql,ds).execute();
                dismiss();
            }
        });
    }
}
