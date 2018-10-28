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

    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
        TABLE_NOW = DBHelper.TABLE_NOW;
        TABLE_HOUR = DBHelper.TABLE_HOUR;
        TABLE_AIR = DBHelper.TABLE_AIR;
        TABLE_FORECAST = DBHelper.TABLE_FORECAST;
    }

    public void addNow(BeanNow item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        BeanNow.Now now = item.getNow();
        values.put("cid", item.getBasic().getCid());
        values.put("condCode", now.getCondCode());
        values.put("condTxt", now.getCondTxt());
        values.put("tmp",now.getTmp());
        values.put("hum", now.getHum());
        values.put("fl", now.getFl());
        values.put("windDir", now.getWindDir());
        values.put("windSc",now.getWindSc());
        values.put("windSpd", now.getWindSpd());
        values.put("loc", item.getUpdate().getLoc());

        db.insert(TABLE_NOW, null, values);
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
            values.put("condTxt", h.getCondTxt());
            values.put("condCode", h.getCondCode());
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
            values.put("tmpMin",f.getTmpMin());
            values.put("tmpMax", f.getTmpMax());
            values.put("condCodeD", f.getCondCodeD());
            values.put("condTxtD", f.getCondTxtD());
            values.put("condCodeN", f.getCondCodeN());
            values.put("condTxtN", f.getCondTxtN());
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
        HashMap<String, String> hash = new HashMap<String,String>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from "+TABLE+" WHERE cid = \""+cid+"\";",null);

        cursor.moveToFirst();
       // Log.i( "AAA", "queryNow: "+cursor.getColumnIndex( "id" ) );
        Log.i( "DBManager", "行数: "+cursor.getCount() );
        Log.i( "DBManager", "列数: "+cursor.getColumnCount() );
        Gson gson = new Gson();

        for(int j = 0; j < cursor.getCount();j++) {
            for (int i = 1; i < cursor.getColumnCount(); i++) {//跳过id自增主键
                hash.put( cursor.getColumnName( i ), cursor.getString( i ) );
                //Log.i( "hash", "queryNow: " + gson.toJson( hash ) );
            }
            //Log.i( "hash", "queryNow: " + gson.toJson( hash ) );
            maps.add( hash );
            if (!cursor.isLast()) {
                cursor.moveToNext();
            }
        }

        gson.toJson( maps );
        Log.i( "Manager输出的map", "queryNow: "+maps );

        cursor.close();
        db.close();
        return maps;
    }

    public void delete(String TABLE_NAME){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(TABLE_NAME,null,null);
       // db.delete(TABLE_AIR,null,null);
       // db.delete(TABLE_HOUR,null,null);
       // db.delete(TABLE_FORECAST,null,null);
        db.close();
    }
}
