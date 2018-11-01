package com.example.administrator.weather.Data;

/**
 * Copyright 2018 aTool.org
 */

import com.google.gson.annotations.SerializedName;

/**
 * Auto-generated: 2018-10-29 0:2:53
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Hour {

    private String condtxt;
    private String condcode;
    private String  tmp;
    private String  time;
    private String  cid;
    public void setCondtxt(String cond_txt) {
        this.condtxt = cond_txt;
    }
    public String getCondtxt() {
        return condtxt;
    }

    public void setCondcode(String cond_code) {
        this.condcode = cond_code;
    }
    public String getCondcode() {
        return condcode;
    }

    public void setTmp(String  tmp) {
        this. tmp =  tmp;
    }
    public String getTmp() {
        return  tmp;
    }

    public void setTime(String  time) {
        this. time =  time;
    }
    public String getTime() {
        return  time;
    }

    public void setCid(String  cid) {
        this. cid =  cid;
    }
    public String getCid() {
        return  cid;
    }

}