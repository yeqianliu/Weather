package com.example.administrator.weather.List;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;

import com.example.administrator.weather.Data.City;
import com.example.administrator.weather.Data.Search;
import com.example.administrator.weather.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GridActivity extends AppCompatActivity implements Runnable {

//    private GridView gridView;
//    private String baseUrl = Environment.getExternalStorageDirectory() + "/下户宝/nextDoorFile/";
//    private List<String> urls = new ArrayList<>();
//    private GridAdapter GridAdapter;

    private SearchView citySearch;
    private GridView citylist;
    Handler handler;
    Search search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.search );

//        //开启⼦线程
//        Thread t = new Thread(this);
//        t.start();
//        handler = new Handler(){
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                if(msg.what==1){
//                    Gson gson = new Gson();
//                    Search search = new Search();
//                    ArrayList<City> results = search.getResults( GridActivity.this );
//                    Log.i( "222222222", "handleMessage: " +gson.toJson( results ));
//                   // citySearchView( GridActivity.this,results );
//                }
//            }
//        };

        citySearch = (SearchView) findViewById(R.id.searchView);
        citylist = (GridView) findViewById(R.id.citylistView);
//        ArrayList<City> results = search.getResults( GridActivity.this );

        Search searchresult = new Search();

        //citylist.setTextFilterEnabled(true);不过滤，没黑框，或者自定义adapter
        //设置false无法自动获取焦点，防止软键盘自动跳出。
        citySearch.setFocusable(false);
        // 设置该SearchView显示搜索按钮
        citySearch.setSubmitButtonEnabled(true);

        // 设置搜索文本监听
        citySearch.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                test( GridActivity.this );
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)){
                    citylist.setFilterText(newText);
                }else{
                    //citylist.clearTextFilter();

                }
                return false;
            }
        });
    }


    public void test(Context context){
        //开启⼦线程
        Thread t = new Thread(this);
        t.start();
        handler = new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==1){
                    Search searchresult = new Search();
                    searchresult.citySearch(GridActivity.this,"bei" );
                    ArrayList<City> results = search.getResults( GridActivity.this );

                    ArrayList<HashMap<String,String>>maps = new ArrayList<HashMap<String,String>>();
                    for(int i=0;i<results.size();i++){
                        HashMap<String, String> map=new HashMap<String, String>();
                        map.put("location",results.get( i ).getLocation());
                        Log.i( "grid", "onCreate: "+results.get( i ).getLocation() );
                        map.put("parent",results.get( i ).getLocation());
                        maps.add(map);
                    }
                    Log.i( "grid", "onCreate: "+maps.get( 1 ).get( "location" ) );

                    SimpleAdapter simpleAdapter=new SimpleAdapter(GridActivity.this,maps,
                            R.layout.city_items,
                            new String[]{"location","parent"},
                            new int[]{R.id.location_list,R.id.parent_list});

                    citylist.setAdapter(simpleAdapter);
                }
            }
        };

    }

    public void citySearchView(Context context, ArrayList<City> results){
        citySearch = (SearchView) findViewById(R.id.searchView);
        citylist = (GridView) findViewById(R.id.citylistView);

        //citylist.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrs));
        ArrayList<HashMap<String,String>>maps = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<results.size();i++){
            HashMap<String, String> map=new HashMap<String, String>();
            map.put("location",results.get( i ).getLocation());
            Log.i( "grid", "onCreate: "+results.get( i ).getLocation() );
            map.put("parent",results.get( i ).getLocation());
            maps.add(map);
        }
        Log.i( "grid", "onCreate: "+maps.get( 1 ).get( "location" ) );

        SimpleAdapter simpleAdapter=new SimpleAdapter(context,maps,
                R.layout.city_items,
                new String[]{"location","parent"},
                new int[]{R.id.location_list,R.id.parent_list});

        citylist.setAdapter(simpleAdapter);

        //citylist.setTextFilterEnabled(true);不过滤，没黑框，或者自定义adapter
        //设置false无法自动获取焦点，防止软键盘自动跳出。
        citySearch.setFocusable(false);
        // 设置该SearchView显示搜索按钮
        citySearch.setSubmitButtonEnabled(false);

        // 设置搜索文本监听
        citySearch.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)){
                    citylist.setFilterText(newText);
                }else{
                    //citylist.clearTextFilter();
                    // Search citySearch = new Search( GridActivity.this,newText);

                }
                return false;
            }
        });

    }

    public void run() {
        Log.i("handler", "run: run()......");
        Search searchresult = new Search(  );
        searchresult.citySearch(this,"bei" );
        //获取Msg对象，用于返回主线程this,"bei"
        Message msg = handler.obtainMessage();
        msg.what = 1;
        msg.obj = searchresult;
        handler.sendMessage(msg);
    }

}

