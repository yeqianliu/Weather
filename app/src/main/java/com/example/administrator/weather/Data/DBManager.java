package com.example.administrator.weather.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.gson.Gson;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DBManager {
    private DBHelper dbHelper;
    public String TABLE_NOW;
    public String TABLE_HOUR;
    public String TABLE_AIR;
    public String TABLE_FORECAST;
    public String TABLE_CITY;

    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
        TABLE_NOW = DBHelper.TABLE_NOW;
        TABLE_HOUR = DBHelper.TABLE_HOUR;
        TABLE_AIR = DBHelper.TABLE_AIR;
        TABLE_FORECAST = DBHelper.TABLE_FORECAST;
        TABLE_CITY = DBHelper.TABLE_CITY;
    }

    public void addAll(BeanNow now,BeanAir air,BeanHour hour,BeanForecast forecast) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //开启事务
        db.beginTransaction();
        try
        {
            addAir( air );
            addForecast( forecast );
            addHour( hour );
            addNow( now );
            //设置事务标志为成功，当结束事务时就会提交事务
            db.setTransactionSuccessful();
        }
        catch(Exception e){
            throw(e);
        }
        finally
        {
            //结束事务
            db.endTransaction();
        }
    }

    public void addNow(BeanNow item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        BeanNow.Now now = item.getNow();
        Gson gson = new Gson();
        Log.i( "插入now", "addNow: "+item.getBasic().getLocation());
        values.put("cid", item.getBasic().getCid());
        values.put("condcode", now.getCondcode());
        values.put("condtxt", now.getCondtxt());
        values.put("tmp",now.getTmp());
        values.put("hum", now.getHum());
        values.put("fl", now.getFl());
        values.put("winddir", now.getWinddir());
        values.put("windsc",now.getWindsc());
        values.put("windspd", now.getWindspd());
        values.put("loc", item.getUpdate().getLoc());
        values.put( "location",item.getBasic().getLocation() );

        db.insert(TABLE_NOW, null, values);
        db.close();
    }

    public void addCity( ArrayList<City> cities){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        for(City city :cities){
            values.put("cid", city.getCid());
            values.put("location", city.getLocation());
            values.put("parent",city.getParent());

            db.insert(TABLE_CITY, null, values);
        }

        db.close();
    }

    public void addAir(BeanAir item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        BeanAir.AirNowCity air = item.getAirNowCity();
        values.put("cid", item.getBasic().getCid());
        values.put("aqi", air.getAqi());
        values.put("main",air.getMain());
        values.put("pm10", air.getPm10());
        values.put("pm25", air.getPm25());
        values.put("no2",air.getNo2());
        values.put("o3", air.getO3());
        values.put("co", air.getCo());
        values.put("qlty", air.getQlty());

        db.insert(TABLE_AIR, null, values);
        db.close();
    }

    public void addHour(BeanHour item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        List<BeanHour.Hourly> hourly = item.getHourly();
        for(BeanHour.Hourly h:hourly){
            values.put("cid", item.getBasic().getCid());
            values.put("time", h.getTime());
            values.put("tmp",h.getTmp());
            values.put("condtxt", h.getCondTxt());
            values.put("condcode", h.getCondCode());
            db.insert(TABLE_HOUR, null, values);
        }
        db.close();
    }

    public void addForecast(BeanForecast item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        List<BeanForecast.DailyForecast> dailyForecast = item.getDailyForecast();
        for(BeanForecast.DailyForecast f:dailyForecast){
            values.put("cid", item.getBasic().getCid());
            values.put("date", f.getDate());
            values.put("tmpmin",f.getTmpmin());
            values.put("tmpmax", f.getTmpmax());
            values.put("condcoded", f.getCondcoded());
            values.put("condtxtd", f.getCondtxtd());
            values.put("condcoden", f.getCondcoden());
            values.put("condtxtn", f.getCondtxtn());
            values.put("sr", f.getSr());
            values.put("ss", f.getSs());
            db.insert(TABLE_FORECAST, null, values);
        }

        db.close();
    }


   //cursor的遍历 https://blog.csdn.net/android_zyf/article/details/53420267
    //cursor起始为-1。。。。
    public ArrayList<HashMap<String, String>> query(String cid,String TABLE) throws SQLException {

        ArrayList<HashMap<String,String>> maps = new ArrayList<HashMap<String, String>>( );
        //HashMap<String, String> hash = new HashMap<String,String>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from "+TABLE+" WHERE cid = \""+cid+"\";",null);


       // Log.i( "AAA", "loadData: "+cursor.getColumnIndex( "id" ) );
        Log.i( "DBManager", "行数: "+cursor.getCount() );
        Log.i( "DBManager", "列数: "+cursor.getColumnCount() );

        Gson gson = new Gson();
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
        return maps;
    }

    public void delete(String TABLE_NAME){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
        db.close();
    }

    public void deleteAll(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NOW,null,null);
        db.delete(TABLE_FORECAST,null,null);
        db.delete(TABLE_HOUR,null,null);
        db.delete(TABLE_AIR,null,null);
        db.close();
    }
}
