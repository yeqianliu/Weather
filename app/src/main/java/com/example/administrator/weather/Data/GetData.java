package com.example.administrator.weather.Data;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetData {

    Now now = new Now();
    Air air = new Air();
    ArrayList<Hour> hours = new ArrayList<Hour>(  );
    ArrayList<Forecast> forecasts = new ArrayList<Forecast>(  );


    DBManager dbManager;
    public GetData(Context context,String cid) {
        dbManager = new DBManager(context);
        try {
            loadData( cid,dbManager.TABLE_NOW );
            loadData( cid,dbManager.TABLE_AIR );
            loadData( cid,dbManager.TABLE_HOUR );
            loadData( cid,dbManager.TABLE_FORECAST );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadData(String cid, String TABLE) throws SQLException {

        ArrayList<HashMap<String,String>> maps = new ArrayList<HashMap<String, String>>( );
        Gson gson = new Gson();
        maps = dbManager.query( cid,TABLE );

        if(TABLE==dbManager.TABLE_NOW){
            String s=gson.toJson( maps.get( 0 ));
            //Log.i( "nnnnnnnn", "loadData: " +s);
            now = gson.fromJson(s,Now.class);
        }

        else if(TABLE==dbManager.TABLE_AIR){
            String s=gson.toJson( maps.get( 0 ));
           // Log.i( "aaaaaaa", "loadData: " +s);
            air = gson.fromJson(s,Air.class);
            //Log.i( "air.getAqi", "Time: " +air.getAqi());
        }

        else if(TABLE==dbManager.TABLE_HOUR){
            for (int i=0;i<maps.size();i++) {
                String s=gson.toJson( maps.get( i ));
                //Log.i( "hhhhhhhh", "loadData: " +s);
                Hour hour = gson.fromJson( s, Hour.class);
                //Log.i( "hour.getTime", "Time: " +hour.getTime());
                hours.add(hour);
            }
        }

        else if(TABLE==dbManager.TABLE_FORECAST){
            for (int i=0;i<maps.size();i++) {
                String s=gson.toJson( maps.get( i ));
                //Log.i( "fffffffffff", "loadData: " +s);
                Forecast forecast = gson.fromJson( s, Forecast.class );
                //Log.i( "forecast.getTmpmax", "tmpMax: " +forecast.getDate());
                forecasts.add( forecast );
            }
        }
    }

    public Now getNow() {
        return now;
    }

    public Air getAir() {
        return air;
    }

    public List<Hour> getHours() {
        return hours;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }
}
