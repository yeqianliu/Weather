package com.example.administrator.weather.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.administrator.weather.List.GridActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.view.HeConfig;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;

public class Search {
    interfaces.heweather.com.interfacesmodule.view.HeWeather HeWeather  = new HeWeather();

    public void citySearch(final Context context, String location){

        HeConfig.init("HE1810211323041610","ad11ae24575d46e7b5614986a9ae5111");
        HeConfig.switchToFreeServerNode();

        HeWeather.getSearch(context,location,"CN",20, Lang.CHINESE_SIMPLIFIED,new HeWeather.OnResultSearchBeansListener(){
            @Override
            public void onError(Throwable e) {
                Log.i("Log", "onError: ", e);
                //查不到略略略
            }

            @Override
            public void onSuccess(interfaces.heweather.com.interfacesmodule.bean.search.Search search) {
                ArrayList<City> cities = new ArrayList<>(  );
                Gson gson = new Gson();
                String s = gson.toJson(search);
                Log.i("城市", "onSuccess: "+ s);

                try {
                    JSONObject jObject1 = new JSONObject(s);
                    //解析第二层----数组
                    JSONArray jsonArray2=jObject1.getJSONArray("basic");
                    for(int i=0;i<jsonArray2.length();i++){
                        //解析第三层----对象
                        JSONObject jObject3=jsonArray2.getJSONObject(i);
                        City city = new City();
                        city.setCid( jObject3.getString( "cid" ) );
                        city.setLocation( jObject3.getString( "location" ) );
                        if(jObject3.getString( "parent_city" ) !=null){
                            city.setParent( jObject3.getString( "parent_city" ) );
                        }
                        cities.add( city );

                    }
                    DBManager db = new DBManager( context);
                    db.delete(db.TABLE_CITY);
                    db.addCity(cities);
                    Log.i("cities1111111", "onSuccess: ");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public ArrayList<City> getResults(Context context) {
        DBManager dbManager = new DBManager(context);
        ArrayList<HashMap<String,String>> maps = new ArrayList<HashMap<String, String>>( );
        Gson gson = new Gson();

        //HashMap<String, String> hash = new HashMap<String,String>();
        DBHelper dbHelper =new DBHelper( context );
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from "+dbManager.TABLE_CITY+" ;",null);

        // Log.i( "AAA", "loadData: "+cursor.getColumnIndex( "id" ) );
        Log.i( "DBCity", "行数: "+cursor.getCount() );
        Log.i( "DBCity", "列数: "+cursor.getColumnCount() );

        cursor.moveToFirst();
        if(cursor!=null){
            do{
                HashMap<String, String> hash = new HashMap<String,String>();
                for (int i = 1; i < cursor.getColumnCount(); i++) {//跳过id自增主键

                    hash.put( cursor.getColumnName( i ), cursor.getString( i ) );
                }
                Log.i( "hash", "loadData: " + gson.toJson( hash ) );
                maps.add( hash );
                Log.i( "aaaaaaaaaaa", "loadData: "+maps);

            }while (cursor.moveToNext());
        }

        Log.i( "Manager输出的map", "loadData: "+maps);

        cursor.close();
        db.close();
        ArrayList<City> cities = new ArrayList<>(  );
        for (int i=0;i<maps.size();i++) {
            String s=gson.toJson( maps.get( i ));
            //Log.i( "hhhhhhhh", "loadData: " +s);
            City city = gson.fromJson( s, City.class);
            //Log.i( "hour.getTime", "Time: " +hour.getTime());
            cities.add(city);
        }
        return cities;
    }

}
