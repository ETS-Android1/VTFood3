package com.se114k11pmcl.group72.vtfood.tool;

import com.se114k11pmcl.group72.vtfood.object.BanAn;
import com.se114k11pmcl.group72.vtfood.object.MonAn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Convert {
    public ArrayList<MonAn> jsonToMonAn(String data){
        ArrayList<MonAn> arr = new ArrayList<>();
        try{
            JSONArray array = new JSONArray(data);
            for (int i = 0; i < array.length(); i ++)
            {
                JSONObject oj = array.getJSONObject(i);
                MonAn mn = new MonAn();
                mn.idf = Integer.parseInt(oj.getString("idf"));
                mn.namef = oj.getString("namef");
                mn.kind = oj.getString("kind");
                mn.cost = oj.getString("cost");
                mn.picture = oj.getString("picture");
                String deal = oj.getString("deal");
                if ( deal.equals("1")){
                    mn.deal = true;
                }else {
                    mn.deal = false;
                }
                arr.add(mn);
            }
        }catch(JSONException e){

        }
        return arr;
    }


    public ArrayList<BanAn> jsonToBanAn(String data){
        ArrayList<BanAn> arr = new ArrayList<>();
        try{
            JSONArray array = new JSONArray(data);
            for (int i = 0; i < array.length(); i ++)
            {
                JSONObject oj = array.getJSONObject(i);
                BanAn ban = new BanAn();
                ban.idt = Integer.parseInt(oj.getString("idt"));
                ban.namet = oj.getString("namet");
                arr.add(ban);
            }
        }catch(JSONException e){

        }
        return arr;
    }
}
