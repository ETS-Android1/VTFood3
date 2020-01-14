package com.se114k11pmcl.group72.vtfood.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.se114k11pmcl.group72.vtfood.Data;
import com.se114k11pmcl.group72.vtfood.R;
import com.se114k11pmcl.group72.vtfood.object.MonAn;

public class DatMonActivity extends AppCompatActivity {
    MonAn monAn;
TextView txvNameFood;
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
    }
    public void setUp(){
        txvNameFood.setText(monAn.namef);
    }
    public void setClick(){}
}
