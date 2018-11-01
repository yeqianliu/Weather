package com.example.administrator.weather;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.weather.Connect.NetWorkReceiver;
import com.example.administrator.weather.Data.City;
import com.example.administrator.weather.Data.GetData;
import com.example.administrator.weather.Data.Search;
import com.example.administrator.weather.Data.SetData;
import com.example.administrator.weather.List.GridAdapter;
import com.example.administrator.weather.List.GridItem;
import com.example.ccy.miuiweatherline.MiuiWeatherView;
import com.example.ccy.miuiweatherline.WeatherBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Runnable{
    //网络状况接收
    NetWorkReceiver netWorkStateReceiver = new NetWorkReceiver();
    Handler handler;
    //SetData datainit;
    GetData data;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.icon_menu);

        //此处需要service
        handler = new Handler(){
            public void handleMessage(Message msg) {
                if(msg.what==1){
                    //ArrayList<City> cities = (ArrayList<City>) msg.obj;

                    //SetData datainit = (SetData)msg.obj;
                    //Log.d( "DATAINIT", "handleMessage: " +datainit.sign);
                    data = new GetData( MainActivity.this,"CN101010100" );
                    init(MainActivity.this, data );
                    //Log.i( "grid", "onCreate: "+cities.get( 0 ).getLocation() );
                }
                super.handleMessage(msg);
            }
        };
        //开启⼦线程
        Thread t = new Thread(this);
        t.start();


    }

    @Override
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

    private void init(Context context,GetData data){

        TextView location = (TextView)findViewById( R.id.location );
        TextView tmpNow = (TextView)findViewById( R.id.tmpNow );
        TextView tmpMax = (TextView)findViewById( R.id.tmpmax );
        TextView tmpMin = (TextView)findViewById( R.id.tmpmin );
        ImageView cond_code = (ImageView)findViewById( R.id.cond_code );
        TextView cond_txt = (TextView)findViewById( R.id.cond_txt );
        TextView pm25 = (TextView)findViewById( R.id.pm25 );

         location.setText( data.getNow().getLocation() );
        tmpNow.setText(data.getNow().getTmp()+"°");
        tmpMax.setText( data.getForecasts().get( 0 ).getTmpmax()+"°" );
        tmpMin.setText( data.getForecasts().get(0).getTmpmin()+"°" );
        cond_txt.setText( data.getNow().getCondtxt() );
        pm25.setText("空气质量  " +data.getAir().getQlty());
        setImage( this,data.getNow().getCondcode(),cond_code );


        initSeven( this,data );
        init24( this,data );



    }

    public void initSeven (Context context,GetData data){

        // 绑定Layout里面的ListView
        gridView = (GridView) findViewById(R.id.sevenListView);

        ArrayList<GridItem> gridItems = new ArrayList<GridItem>();
        for(int i=0;i<7;i++){
            String imageUrl = "https://cdn.heweather.com/cond_icon/"+data.getForecasts().get( i ).getCondcoded()+".png";
            String date = data.getForecasts().get( i ).getDate().substring( 5 );
            String text = data.getForecasts().get( i ).getCondtxtd();
            GridItem gridItem = new GridItem(imageUrl,date,text);
            gridItems.add( gridItem );
            //Log.i( "grid", "init: " +gridItem.getImageUrl());
        }

        gridView.setAdapter(new GridAdapter(context,R.layout.list_items,gridItems));

    }


    //https://blog.csdn.net/ccy0122/article/details/76464825
    //https://github.com/CCY0122/miuiweatherline
    public void init24(Context context,GetData data){
        MiuiWeatherView weatherView = (MiuiWeatherView) findViewById( R.id.weather );
        List<WeatherBean> hour24 = new ArrayList<>();

        for(int i=0;i<data.getHours().size();i++){
            int tmp = Integer.parseInt( data.getHours().get( i ).getTmp());
            //Log.i( "2444444", "init24: " +data.getHours().get( i ).getTime());
            String time = data.getHours().get( i ).getTime().substring(11);
            String weather = "";
            switch (data.getHours().get( i ).getCondcode()){
                case "100":
                    weather = WeatherBean.SUN;
                    break;
                case "101":
                    weather = WeatherBean.CLOUDY;
                    break;
                case "399":
                    weather = WeatherBean.RAIN;
                    break;
                case "499":
                    weather = WeatherBean.SNOW;
                    break;
                case "104":
                    weather = WeatherBean.SUN_CLOUD;
                    break;
                case "302":
                    weather = WeatherBean.THUNDER;
                    break;
            }
            WeatherBean b1 = new WeatherBean(weather,tmp,time);
            hour24.add(b1);
        }
        weatherView.setData(hour24);
    }

    public void setImage(Context context,String code,ImageView imageView){
        String imageUrl = "https://cdn.heweather.com/cond_icon/"+code+".png";
        Glide.with(context)
                .load(imageUrl)
                .into(imageView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu items for use in the action bar
     MenuInflater inflater = getMenuInflater();
     inflater.inflate(R.menu.menu_main, menu);
     return super.onCreateOptionsMenu(menu);
    }
    @Override
    public void run() {
        Log.i("handler", "run: run()......");
        SetData datainit = new SetData(this,"CN101010100");
        //Search search = new Search( this,"bei" );
        //List<City> cities = search.getCities();

        //获取Msg对象，用于返回主线程
        Message msg = handler.obtainMessage();
        msg.what = 1;
        msg.obj = datainit;
        //msg.obj = cities;
        handler.sendMessage(msg);
    }
}


