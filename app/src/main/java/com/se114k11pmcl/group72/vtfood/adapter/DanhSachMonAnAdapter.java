package com.se114k11pmcl.group72.vtfood.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.se114k11pmcl.group72.vtfood.R;
import com.se114k11pmcl.group72.vtfood.object.MonAn;

import java.util.ArrayList;
import java.util.List;

public class DanhSachMonAnAdapter extends ArrayAdapter<MonAn> {
    Context myCt;
    public ArrayList<MonAn> myArr;
    public DanhSachMonAnAdapter(Context context, int resource, List<MonAn> objects){
        super(context, resource, objects);
        this.myCt = context;
        this.myArr = new ArrayList<>(objects);
    }
    public void updateMonAn(ArrayList<MonAn>arr){
        this.myArr = arr;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.myArr.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)myCt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_mon_an, null);
        }
        if(myArr.size()>0){
            TextView txvTenMonAn = convertView.findViewById(R.id.txvTenMonAn);
            TextView txvLoaiThucAn = convertView.findViewById(R.id.txvLoaiThucAn);
            TextView txvDonGia = convertView.findViewById(R.id.txvDonGia);
            TextView txvLoaiDeal = convertView.findViewById(R.id.txvLoaiDeal);

            MonAn mn = myArr.get(position);
            txvTenMonAn.setText(mn.namef);
            txvLoaiThucAn.setText(mn.kind);
            if (mn.deal){
                txvLoaiDeal.setText("DISCOUNT");
            }else {
                txvLoaiDeal.setText("SPECIALS");
            }
            txvDonGia.setText("" + mn.cost +" VNĐ");
        }
        return convertView;
    }
}
