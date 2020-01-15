package com.se114k11pmcl.group72.vtfood.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.se114k11pmcl.group72.vtfood.Data;
import com.se114k11pmcl.group72.vtfood.R;
import com.se114k11pmcl.group72.vtfood.object.BanAn;
import com.se114k11pmcl.group72.vtfood.object.MonAn;

public class DatMonActivity extends AppCompatActivity {
    MonAn monAn;
    TextView txvNameFood;
    Spinner spnBanAn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_mon);
        init();
        anhXa();
        setUp();
        setClick();
    }
    public void init(){
        for ( MonAn k : Data.getData().arrMonAn){
            if ( k.idf == Data.getData().idMonAnCanSua){
                monAn = k;
                return;
            }
        }
    }
    public void anhXa(){
        txvNameFood = findViewById(R.id.txvNameFood);
        spnBanAn = findViewById(R.id.spnBanAn);
    }
    public void setUp(){
        txvNameFood.setText(monAn.namef);
        spnBanAn.setAdapter(new ArrayAdapter<BanAn>(this,R.layout.item_text_ban_an, Data.getData().arrBanAn));
    }
    public void setClick(){}
}
