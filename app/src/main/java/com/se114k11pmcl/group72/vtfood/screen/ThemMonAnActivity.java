package com.se114k11pmcl.group72.vtfood.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.se114k11pmcl.group72.vtfood.Data;
import com.se114k11pmcl.group72.vtfood.R;
import com.se114k11pmcl.group72.vtfood.api.ApiRunSQL;
import com.se114k11pmcl.group72.vtfood.api.RunSQL;
import com.se114k11pmcl.group72.vtfood.object.MonAn;

public class ThemMonAnActivity extends AppCompatActivity implements ApiRunSQL {
EditText edtFoodName, edtFoodKind, edtDonGia;
TextView txvAdd_Discounts, txvAdd_Specials;
boolean deal = true;
MonAn monAn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_mon_an);
        init();
        anhXa();
        setUp();
        setClick();
    }
    private void init(){
        if ( Data.getData().idMonAnCanSua==-1){
            ((TextView)findViewById(R.id.btnAdd)).setText("THÊM");
            monAn = null;
        }else{
            ((TextView)findViewById(R.id.btnAdd)).setText("SỬA");
            for ( MonAn k : Data.getData().arrMonAn){
                if ( k.idf == Data.getData().idMonAnCanSua){
                    monAn = k;
                    return;
                }
            }
        }
    }
    private void anhXa(){
        edtFoodName = findViewById(R.id.edtFoodName);
        edtFoodKind = findViewById(R.id.edtFoodKind);
        edtDonGia = findViewById(R.id.edtDonGia);
        txvAdd_Discounts = findViewById(R.id.txvAdd_Discounts);
        txvAdd_Specials = findViewById(R.id.txvAdd_Specials);
    }
    private void setUp(){
        if (monAn!=null){
            edtFoodName.setText(monAn.namef);
            edtFoodKind.setText(monAn.kind);
            edtDonGia.setText(monAn.cost);
            if(monAn.deal){
                deal = true;
                txvAdd_Discounts.setTextColor(ThemMonAnActivity.this.getResources().getColor(R.color.vang));
                txvAdd_Discounts.setBackgroundColor(ThemMonAnActivity.this.getResources().getColor(R.color.den));
                txvAdd_Specials.setTextColor(ThemMonAnActivity.this.getResources().getColor(R.color.den));
                txvAdd_Specials.setBackgroundColor(ThemMonAnActivity.this.getResources().getColor(R.color.trang));
            }else{
                deal = false;
                txvAdd_Specials.setTextColor(ThemMonAnActivity.this.getResources().getColor(R.color.vang));
                txvAdd_Specials.setBackgroundColor(ThemMonAnActivity.this.getResources().getColor(R.color.den));
                txvAdd_Discounts.setTextColor(ThemMonAnActivity.this.getResources().getColor(R.color.den));
                txvAdd_Discounts.setBackgroundColor(ThemMonAnActivity.this.getResources().getColor(R.color.trang));
            }
        }
    }



    private void setClick(){
        txvAdd_Discounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deal = true;
                txvAdd_Discounts.setTextColor(ThemMonAnActivity.this.getResources().getColor(R.color.vang));
                txvAdd_Discounts.setBackgroundColor(ThemMonAnActivity.this.getResources().getColor(R.color.den));
                txvAdd_Specials.setTextColor(ThemMonAnActivity.this.getResources().getColor(R.color.den));
                txvAdd_Specials.setBackgroundColor(ThemMonAnActivity.this.getResources().getColor(R.color.trang));
            }
        });
        txvAdd_Specials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deal = false;
                txvAdd_Specials.setTextColor(ThemMonAnActivity.this.getResources().getColor(R.color.vang));
                txvAdd_Specials.setBackgroundColor(ThemMonAnActivity.this.getResources().getColor(R.color.den));
                txvAdd_Discounts.setTextColor(ThemMonAnActivity.this.getResources().getColor(R.color.den));
                txvAdd_Discounts.setBackgroundColor(ThemMonAnActivity.this.getResources().getColor(R.color.trang));
            }
        });
    }

    private boolean checkNullInfor(EditText e){
        String s = "" +e.getText();
        if ( s.length()==0){
            return true;
        }else{
            return false;
        }
    }
    private void Warning(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    public void themMonAn(View view) {
        if(checkNullInfor(edtFoodName)){
            Warning("Thiếu tên món ăn");
            return;
        }
        String ten = edtFoodName.getText().toString();
        if(checkNullInfor(edtFoodKind)){
            Warning("Thiếu loại món ăn");
            return;
        }
        String loaiMonAn = edtFoodKind.getText().toString();
        if(checkNullInfor(edtDonGia)){
            Warning("Thiếu giá tiền món ăn");
            return;
        }
        String donGia = edtDonGia.getText().toString();

        String deal = "1";
        if(!this.deal){
            deal="0";
        }

        String sql;
        if(monAn == null) {
            sql = "INSERT INTO `food` (`idf`, `namef`, `kind`, `deal`, `cost`, `picture`) VALUES (NULL, '" +
                    ten +
                    "', '" +
                    loaiMonAn +
                    "', '" +
                    deal +
                    "', '" +
                    donGia +
                    "', '" +
                    "" +
                    "')";
        }else{
            sql = "UPDATE `food` SET " +
                    "`namef` = '" +
                    ten +
                    "', `kind` = '" +
                    loaiMonAn +
                    "', `deal` = '" +
                    deal +
                    "', `cost` = '" +
                    donGia +
                    "' WHERE `food`.`idf` = " + monAn.idf;
        }
        new RunSQL(sql, this).execute();
    }

    @Override
    public void batDauChayCauSQL() {
        Warning("Bắt đầu xử lý");
    }

    @Override
    public void ketThuc() {
        Warning("Hoàn thành");
        Data.getData().arrMonAn.clear();
        onBackPressed();
    }

    @Override
    public void biLoi() {
        Warning("runsql bị lỗi");
    }
}
