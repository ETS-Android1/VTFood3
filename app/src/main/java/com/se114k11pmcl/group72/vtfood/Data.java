package com.se114k11pmcl.group72.vtfood;

import androidx.annotation.Nullable;

import com.se114k11pmcl.group72.vtfood.object.BanAn;
import com.se114k11pmcl.group72.vtfood.object.MonAn;

import java.util.ArrayList;

public class Data {
    static Data data;
    static {
        data = new Data();
    }
    public static Data getData(){
        return data;
    }
    public ArrayList<MonAn> arrMonAn = new ArrayList<>();
    public ArrayList<BanAn> arrBanAn = new ArrayList<>();

// -1 là chuyển sang thêm món ăn ;; != -1 là sửa thông tin món ăn
    public int idMonAnCanSua = -1;

}
