package com.example.administrator.weather;

import android.app.Application;

public class myApplication extends Application {

    //public static RequestQueue requestQueue;
    public static int memoryCacheSize;
    //public static ImageLoader imageLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        // 不必为每一次HTTP请求都创建一个RequestQueue对象，推荐在application中初始化
       // requestQueue = Volley.newRequestQueue(this);
        // 计算内存缓存
       // memoryCacheSize = getMemoryCacheSize();
    }


}
