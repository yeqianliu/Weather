package com.example.administrator.weather.Data;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class GetData {
    ArrayList<HashMap<String,String>> nows;
    ArrayList<HashMap<String,String>> airs;
    ArrayList<HashMap<String,String>> hours;
    ArrayList<HashMap<String,String>> forecasts;
    DBManager dbManager;
    public GetData(Context context,String cid) {
        dbManager = new DBManager(context);
        try {
            nows = dbManager.query( cid,dbManager.TABLE_NOW );
            airs = dbManager.query( cid,dbManager.TABLE_AIR );
            hours = dbManager.query( cid,dbManager.TABLE_HOUR );
            forecasts = dbManager.query( cid,dbManager.TABLE_FORECAST );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<HashMap<String,String>> getNows(){
        return nows;
    }
    public ArrayList<HashMap<String,String>> getHours(){
        return hours;
    }
    public ArrayList<HashMap<String,String>> getAirs(){
        return airs;
    }
    public ArrayList<HashMap<String,String>> getForecasts(){
        return forecasts;
    }

}
