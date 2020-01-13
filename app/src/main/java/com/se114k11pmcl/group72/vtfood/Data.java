package com.se114k11pmcl.group72.vtfood;

import androidx.annotation.Nullable;

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

}
