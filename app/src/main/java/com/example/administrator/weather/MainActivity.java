package com.example.administrator.weather;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.weather.Connect.NetWorkReceiver;
import com.example.administrator.weather.Data.DBManager;
import com.example.administrator.weather.Data.GetData;
import com.example.administrator.weather.Data.SetData;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

//接口回调，HeWeather和Onclicklistener为什么不一样？
public class MainActivity extends AppCompatActivity implements Runnable{
    //网络状况接收
    NetWorkReceiver netWorkStateReceiver = new NetWorkReceiver();
    private ArrayList<HashMap<String, String>> listItems;
    Handler handler;
    SetData datainit;
    GetData data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        listItems = data.getForecasts();
        Gson gson = new Gson();
        gson.toJson( listItems );
        Log.i( "Activity", "queryNow: "+listItems );



        //startActivity(new Intent(this,SetData.class ));



    }

    protected void onResume() {
        //没有网络时Toast提示
        if (netWorkStateReceiver == null) {
            netWorkStateReceiver = new NetWorkReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkStateReceiver, filter);
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(netWorkStateReceiver);
        super.onPause();
    }

    @Override
    public void run() {

    }

   /* @Override
    public void run() {

        Message msg = handler.obtainMessage(5);
        //msg.what = 5;
        msg.obj = "Hello from run()";
        handler.sendMessage(msg);

        if(true){
            msg.arg1 = 1;
        }else{
            msg.arg1 = 0;
        }

    }*/
}
