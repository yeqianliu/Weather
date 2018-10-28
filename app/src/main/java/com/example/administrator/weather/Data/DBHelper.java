package com.example.administrator.weather.Data;
//参考https://blog.csdn.net/nugongahou110/article/details/46926345
//反射建表
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Field;

public class DBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "Weather.db";

    public static final String TABLE_NOW = "now";
    public static final String TABLE_AIR = "air";
    public static final String TABLE_HOUR= "hour";
    public static final String TABLE_FORECAST = "forecast";
    public String test;


    private String SqlCreateNow = "CREATE TABLE " + TABLE_NOW +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "cid TEXT, " +
            "condCode TEXT," +
            "condTxt TEXT," +
            "tmp TEXT," +
            "hum TEXT," +
            "fl TEXT, " +
            "windDir TEXT, " +
            "windSc TEXT, " +
            "windSpd TEXT, " +
            "loc TEXT)";

    private String SqlCreateAir = "CREATE TABLE " + TABLE_AIR +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "cid TEXT, " +
            "aqi TEXT," +
            " main TEXT, " +
            "pm10 TEXT," +
            " pm25 TEXT, " +
            "no2 TEXT, " +
            "o3 TEXT, " +
            "co TEXT)";

    private String SqlCreateHour = "CREATE TABLE " + TABLE_HOUR +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " cid TEXT," +
            " time TEXT," +
            " tmp TEXT, " +
            "condTxt TEXT," +
            " condCode TEXT)";

    private String SqlCreateForecast = "CREATE TABLE " + TABLE_FORECAST +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "cid TEXT, " +
            "date TEXT," +
            "tmpMin TEXT, " +
            "tmpMax TEXT, " +
            "condCodeD TEXT, " +
            "condTxtD TEXT," +
            "condCodeN TEXT, " +
            "condTxtN TEXT, " +
            "sr TEXT, " +
            "ss TEXT)";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        //test = createTable(now, TABLE_NOW);需要在manager、helper传参now
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //调用execSQL创建数据库
        //指定表的名称及其中的列

        db.execSQL( SqlCreateNow );
        db.execSQL( SqlCreateAir );
        db.execSQL( SqlCreateHour );
        db.execSQL( SqlCreateForecast );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS ";
        db.execSQL(sql+ TABLE_NOW);
        onCreate(db);
    }

    private String createTable (BeanNow now , String tableName){
        Class clazz = now.getClass();

        StringBuffer sBuffer = new StringBuffer();
        sBuffer.append("create table if not exists "+ tableName + " "+
                "(_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,");

        //得到实体类中所有的公有属性
        Field[] fields = clazz.getDeclaredFields();
        //遍历所有的公有属性
        for(Field field : fields){
            field.setAccessible(true);
            //如果属性不为_id的话，说明是新的字段
            if (!field.getName().equals("_id")) {
                //得到属性的基本数据类型
                String type = field.getType().getSimpleName();
                //如果是String类型的属性，就把字段类型设置为TEXT
                if (type.equals("String")) {
                    sBuffer.append(field.getName()+" TEXT,");
                }
            }
        }

        Class classes[] = clazz.getDeclaredClasses();
        for (Class c : classes) {//对成员内部类进行反射

            //得到实体类中所有的公有属性
            Field[] f = c.getDeclaredFields();
            //遍历所有的公有属性
            for(Field field : f){
                field.setAccessible(true);
                sBuffer.append(field.getName()+" TEXT,");
            }
        }


        //将最后的逗号删除
        sBuffer.deleteCharAt(sBuffer.length()-1);
        //替换成); 表明sql语句结束
        sBuffer.append(");");
        Log.i("reflect", sBuffer.toString());
        //返回这条sql语句
        return sBuffer.toString();

    }

}
