package com.example.administrator.weather.Data;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.Unit;
import interfaces.heweather.com.interfacesmodule.bean.air.now.AirNow;
import interfaces.heweather.com.interfacesmodule.bean.search.Search;
import interfaces.heweather.com.interfacesmodule.bean.weather.forecast.Forecast;
import interfaces.heweather.com.interfacesmodule.view.HeConfig;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;

//接口回调，HeWeather和Onclicklistener为什么不一样？
public class SetData {
    BeanNow now = new BeanNow();
    BeanAir air = new BeanAir();
    BeanHour hour = new BeanHour();
    BeanForecast forecast = new BeanForecast();
//    public String sign = "11111111";
//
//    public String getSign() {
//        return sign;
//    }

    interfaces.heweather.com.interfacesmodule.view.HeWeather HeWeather  = new HeWeather();
    //通过构造函数来获取

    Gson gson = new Gson();
    //通过 GsonBuilder 来获取，可以进行多项特殊配置
    //Gson gson = new GsonBuilder().create();
    public  SetData(final Context context,String location){

        HeConfig.init("HE1810211323041610","ad11ae24575d46e7b5614986a9ae5111");
        HeConfig.switchToFreeServerNode();

        HeWeather.getWeatherNow(context, location, Lang.CHINESE_SIMPLIFIED, Unit.METRIC,
            new HeWeather.OnResultWeatherNowBeanListener() {
            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Log.i("Now", "未获取数据: ");
            }

            @Override
            public void onSuccess(List dataObject) {
                String data = gson.toJson(dataObject);
                data = data.substring(1,data.length()-1);
                //数组的方法
               // ArrayList<NowBeanTest> list=gson.fromJson(data,
               //         new TypeToken<ArrayList<NowBeanTest>>(){}.getType());
                Log.i("Now", "onSuccess: "+data);
                now = gson.fromJson(data,BeanNow.class);
                Log.i( "now", "addNow: "+gson.toJson( now ) );
                DBManager db = new DBManager(context);
                db.delete(db.TABLE_NOW);
                db.addNow(now);
            }
        });

        HeWeather.getWeatherHourly(context,location, new HeWeather.OnResultWeatherHourlyBeanListener(){
            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onSuccess(List dataObject) {
                String data = gson.toJson(dataObject);
                data = data.substring(1,data.length()-1);
                //Log.i("Hour", "onSuccess: " + data);
                hour = gson.fromJson(data,BeanHour.class);
                DBManager db = new DBManager(context);
                db.delete(db.TABLE_HOUR);
                db.addHour(hour);
                Log.i("Hour", "onSuccess: " + hour.getUpdate().getLoc());
            }
        });

        HeWeather.getWeatherForecast(context,location, new HeWeather.OnResultWeatherForecastBeanListener (){
            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onSuccess(List<Forecast> list) {
                String data = gson.toJson(list);
                data = data.substring(1,data.length()-1);
                //Log.i("Forecast", "onSuccess: " + data);
                forecast = gson.fromJson(data,BeanForecast.class);
                DBManager db = new DBManager(context);
                db.delete(db.TABLE_FORECAST);
                db.addForecast(forecast);
                Log.i("Forecast", "onSuccess: "+forecast.getBasic().getLocation());
            }
        });

        HeWeather.getAirNow(context,location, new HeWeather.OnResultAirNowBeansListener  (){
                    @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onSuccess(List<AirNow> list) {
                String data = gson.toJson(list);
                data = data.substring(1,data.length()-1);
            //    Log.i("Airnow", "onSuccess: " + data);
                air = gson.fromJson(data,BeanAir.class);
                DBManager db = new DBManager(context);
                db.delete(db.TABLE_AIR);
                db.addAir(air);
                Log.i("Air", "onSuccess: "+air.getBasic().getLon());
            }
        });

//        HeWeather.getSearch(context,"北京","world",20, Lang.CHINESE_SIMPLIFIED,new HeWeather.OnResultSearchBeansListener(){
//            @Override
//            public void onError(Throwable e) {
//                Log.i("Log", "onError: ", e);
//                //查不到略略略
//            }
//
//            @Override
//            public void onSuccess(interfaces.heweather.com.interfacesmodule.bean.search.Search search) {
//                ArrayList<City> cities = new ArrayList<>(  );
//
//                Gson gson = new Gson();
//                String s = gson.toJson(search);
//                Log.i("城市", "onSuccess: "+ s);
//                JSONObject jObject1= null;
//                try {
//                    jObject1 = new JSONObject(s);
//                    //解析第二层----数组
//                    JSONArray jsonArray2=jObject1.getJSONArray("basic");
//                    for(int i=0;i<jsonArray2.length();i++){
//                        //解析第三层----对象
//                        JSONObject jObject3=jsonArray2.getJSONObject(i);
//                        City city = new City();
//                        city.setCid( jObject3.getString( "cid" ) );
//                        city.setLocation( jObject3.getString( "location" ) );
//                        city.setParent( jObject3.getString( "parent_city" ) );
//                        cities.add( city );
//                        Log.i( "citySearch", "onSuccess: "+city.getLocation() );
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        });
    }
}
