package com.example.administrator.weather.Data;

/**
 * Copyright 2018 aTool.org
 */
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Forecast {

    private String date;
    private String  condtxtd;
    private String  condcoden;
    private String  ss;
    private String  condtxtn;
    private String  condcoded;
    private String  tmpmax;
    private String  tmpmin;
    private String  cid;
    private String  sr;

    public Forecast(){

    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }

    public void setCondtxtd(String  condtxtd) {
        this. condtxtd =  condtxtd;
    }
    public String getCondtxtd() {
        return  condtxtd;
    }

    public void setCondcoden(String condcoden) {
        this.condcoden = condcoden;
    }
    public String getCondcoden() {
        return condcoden;
    }

    public void setSs(String  ss) {
        this. ss =  ss;
    }
    public String getSs() {
        return  ss;
    }

    public void setCondtxtn(String  condtxtn) {
        this. condtxtn =  condtxtn;
    }
    public String getCondtxtn() {
        return  condtxtn;
    }

    public void setCondcoded(String  condcoded) {
        this. condcoded =  condcoded;
    }
    public String getCondcoded() {
        return  condcoded;
    }

    public void setTmpmax(String tmpmax) {
        this.tmpmax = tmpmax;
    }
    public String getTmpmax() {
        return tmpmax;
    }

    public void setTmpmin(String  tmpmin) {
        this. tmpmin =  tmpmin;
    }
    public String getTmpmin() {
        return  tmpmin;
    }

    public void setCid(String  cid) {
        this. cid = cid;
    }
    public String getCid() {
        return  cid;
    }

    public void setSr(String  sr) {
        this. sr =  sr;
    }
    public String getSr() {
        return  sr;
    }

}
