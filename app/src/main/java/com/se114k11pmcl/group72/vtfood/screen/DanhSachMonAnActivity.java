package com.se114k11pmcl.group72.vtfood.screen;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.se114k11pmcl.group72.vtfood.Data;
import com.se114k11pmcl.group72.vtfood.KEY;
import com.se114k11pmcl.group72.vtfood.R;
import com.se114k11pmcl.group72.vtfood.adapter.DanhSachMonAnAdapter;
import com.se114k11pmcl.group72.vtfood.api.ApiGetDataFromTableRun;
import com.se114k11pmcl.group72.vtfood.api.ApiRunSQL;
import com.se114k11pmcl.group72.vtfood.api.GetDataFromTable;
import com.se114k11pmcl.group72.vtfood.dialog.AddOptionDialog;
import com.se114k11pmcl.group72.vtfood.dialog.AddTableDialog;
import com.se114k11pmcl.group72.vtfood.dialog.FoodOptionDialog;
import com.se114k11pmcl.group72.vtfood.object.MonAn;
import com.se114k11pmcl.group72.vtfood.tool.Convert;

import java.util.ArrayList;

public class DanhSachMonAnActivity extends AppCompatActivity implements ApiGetDataFromTableRun, ApiRunSQL {
    Convert convert = new Convert();
    DanhSachMonAnAdapter danhSachMonAnAdapter;
    ListView lsvDSMonAn;

    EditText edtFood;
    String nameFood="";

    TextView txvGT_All,txvGT_Discounts,txvGT_Specials;
    TextView arrTxvGT[];
    int chonGTdeal = 0; /// 0 : all ; 1 : Discounts ; 2 : Specials

    int thucHienCauLenh = 0; //0 : thực hiện câu lệnh thêm bàn ; ; 1 : thức hiện câu lệnh xóa món ăn ;;

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_order);
        init();
        anhXa();
        setUp();
        setClick();
        String s = checkDatainDataTable();
        if (s.length()>0){
            new GetDataFromTable( s,this).execute();
        }
    }
    public void init()
    {

    }
    public void anhXa(){
        lsvDSMonAn = findViewById(R.id.lsvDSMonAn);
        edtFood = findViewById(R.id.edtFood);

        txvGT_All = findViewById(R.id.txvGT_All);
        txvGT_Discounts = findViewById(R.id.txvGT_Discounts);
        txvGT_Specials = findViewById(R.id.txvGT_Specials);
    }
    public void setUp(){
        arrTxvGT = new TextView[]{txvGT_All,txvGT_Discounts,txvGT_Specials};
        setUpGT();
    }
    public void setClick(){
        edtFood.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = edtFood.getText().toString();
                if ( str.length()>=2)
                {
                    str = str.toLowerCase();
                    //tiến hành kiểm tra và update list
                    nameFood = str;
                    Testing();
                }else {
                    nameFood="";
                }
                Testing();
            }
        });
        txvGT_All.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonGTdeal = 0;
                setUpGT();
                Testing();
            }
        });
        txvGT_Discounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonGTdeal = 1;
                setUpGT();
                Testing();
            }
        });
        txvGT_Specials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonGTdeal = 2;
                setUpGT();
                Testing();
            }
        });
        lsvDSMonAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                new FoodOptionDialog(DanhSachMonAnActivity.this,danhSachMonAnAdapter.myArr.get(i)).show();
            }
        });
    }

private void Testing(){
    ArrayList<MonAn> arrTenMonAn;
    if (nameFood.length()>=2)
    {
        arrTenMonAn = new ArrayList<>();
        for (MonAn k : Data.getData().arrMonAn)
        {
            String nameMon = k.namef.toLowerCase();
            if (nameMon.indexOf(nameFood)>=0)
                arrTenMonAn.add(k);
        }
    }else{
        arrTenMonAn = Data.getData().arrMonAn;
    }

    danhSachMonAnAdapter.updateMonAn(arrTenMonAn);

    ArrayList<MonAn> arrGTDeal;
    if (chonGTdeal != 0) {
        arrGTDeal = new ArrayList<>();
        boolean GTDeal = false;

        if (chonGTdeal == 1)
            GTDeal = true;

        for (MonAn k : arrTenMonAn) {
            if (k.deal == GTDeal)
                arrGTDeal.add(k);
        }
    }else {
        arrGTDeal = arrTenMonAn;
    }
    danhSachMonAnAdapter.updateMonAn(arrGTDeal);
}

public void setUpGT(){
        for ( TextView txv:arrTxvGT){
            txv.setTextColor(this.getResources().getColor(R.color.den));
            txv.setBackgroundColor(this.getResources().getColor(R.color.trang));
        }
    arrTxvGT[chonGTdeal].setTextColor(this.getResources().getColor(R.color.vang));
    arrTxvGT[chonGTdeal].setBackgroundColor(this.getResources().getColor(R.color.den));
}



    public String checkDatainDataTable(){
        String nameTable = "";
        if (Data.getData().arrMonAn.size()==0){
            return KEY.TABLE_MON_AN;
        }
        return "";
    }

    public void setUpShowData(){
        danhSachMonAnAdapter = new DanhSachMonAnAdapter(this,0,Data.getData().arrMonAn);
        lsvDSMonAn.setAdapter(danhSachMonAnAdapter);
    }

    @Override
    public void batDauLay(){
        Toast.makeText(this,"Bắt đầu lấy dữ liệu", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data, String tableName){
        if(tableName.equals(KEY.TABLE_MON_AN)){
            Data.getData().arrMonAn.clear();
            Data.getData().arrMonAn.addAll(convert.jsonToMonAn(data));
        }String s = checkDatainDataTable();
        if (s.length()>0){
            new GetDataFromTable( s,this).execute();
        }else{
            setUpShowData();
        }
    }

    public static int ID_THEM_MON_AN = 99;
    public static int ID_SUA_MON_AN = 100;

    @Override
    public void batDauChayCauSQL() {

    }

    @Override
    public void ketThuc() {
        if ( thucHienCauLenh == 0 ) {
            Toast.makeText(this, "Thêm bàn thành công !", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void biLoi(){
        Toast.makeText(this,"Lỗi kết nối", Toast.LENGTH_SHORT).show();
    }

    public void AddOption(View view) {
        new AddOptionDialog(this).show();
    }
    public void moveToAddFood(){
        Data.getData().idMonAnCanSua = -1;
            Intent i = new Intent(this,ThemMonAnActivity.class);
            startActivityForResult(i,ID_THEM_MON_AN);

    }
    public void themBan(){
        thucHienCauLenh = 0;
        new AddTableDialog(this).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode == ID_THEM_MON_AN){
            String s = checkDatainDataTable();
            if (s.length()>0){
                new GetDataFromTable( s,this).execute();
            }
        }else if(requestCode == ID_SUA_MON_AN){
            String s = checkDatainDataTable();
            if (s.length()>0){
                new GetDataFromTable( s,this).execute();
            }
        }
    }
}
