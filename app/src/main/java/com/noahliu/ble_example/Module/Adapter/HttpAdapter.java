package com.noahliu.ble_example.Module.Adapter;

import android.app.Activity;

import com.noahliu.ble_example.Module.Enitiy.ScannedData;
import com.noahliu.ble_example.Module.Service.HttpService;

import java.util.ArrayList;
import java.util.List;

public class HttpAdapter {
    private List<ScannedData> arrayList = new ArrayList<>();
    private HttpService httpService;
    /**清除搜尋到的裝置列表*/

    public HttpAdapter(List<ScannedData> arrayList){
        this.arrayList=arrayList;
    }
    public void clearDevice(){
        this.arrayList.clear();
    }
    /**send ScannedData to web server*/
    public void sendToHTTP(){
        for(int i=0;i<arrayList.size();i++)
        this.httpService.post(arrayList.get(i));
    }

}
