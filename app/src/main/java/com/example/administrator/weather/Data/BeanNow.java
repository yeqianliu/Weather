package com.example.administrator.weather.Data;


import com.google.gson.annotations.SerializedName;

public class BeanNow {
    public String TableName= "now";
    private Now now;
    private Basic basic;
    private String status;
    private Update update;

    public void setNow(Now now) {
        this.now = now;
    }

    public Now getNow() {
        return now;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    public Basic getBasic() {
        return basic;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }

    public Update getUpdate() {
        return update;
    }


    public class Now {
        private String cloud;
        @SerializedName("cond_code")
        private String condCode;
        @SerializedName("cond_txt")
        private String condTxt;
        private String fl;
        private String hum;
        private String pcpn;
        private String pres;
        private String tmp;
        private String vis;
        @SerializedName("wind_deg")
        private String windDeg;
        @SerializedName("wind_dir")
        private String windDir;
        @SerializedName("wind_sc")
        private String windSc;
        @SerializedName("wind_spd")
        private String windSpd;

        public void setCloud(String cloud) {
            this.cloud = cloud;
        }

        public String getCloud() {
            return cloud;
        }

        public void setCondCode(String condCode) {
            this.condCode = condCode;
        }

        public String getCondCode() {
            return condCode;
        }

        public void setCondTxt(String condTxt) {
            this.condTxt = condTxt;
        }

        public String getCondTxt() {
            return condTxt;
        }

        public void setFl(String fl) {
            this.fl = fl;
        }

        public String getFl() {
            return fl;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getHum() {
            return hum;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
        }

        public String getPcpn() {
            return pcpn;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public String getPres() {
            return pres;
        }

        public void setTmp(String tmp) {
            this.tmp = tmp;
        }

        public String getTmp() {
            return tmp;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public String getVis() {
            return vis;
        }

        public void setWindDeg(String windDeg) {
            this.windDeg = windDeg;
        }

        public String getWindDeg() {
            return windDeg;
        }

        public void setWindDir(String windDir) {
            this.windDir = windDir;
        }

        public String getWindDir() {
            return windDir;
        }

        public void setWindSc(String windSc) {
            this.windSc = windSc;
        }

        public String getWindSc() {
            return windSc;
        }

        public void setWindSpd(String windSpd) {
            this.windSpd = windSpd;
        }

        public String getWindSpd() {
            return windSpd;
        }

    }


    public class Basic {

        @SerializedName("admin_area")
        private String adminArea;
        private String cid;
        private String cnty;
        private String lat;
        private String location;
        private String lon;
        @SerializedName("parent_city")
        private String parentCity;
        private String tz;

        public void setAdminArea(String adminArea) {
            this.adminArea = adminArea;
        }

        public String getAdminArea() {
            return adminArea;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getCid() {
            return cid;
        }

        public void setCnty(String cnty) {
            this.cnty = cnty;
        }

        public String getCnty() {
            return cnty;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLat() {
            return lat;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getLocation() {
            return location;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getLon() {
            return lon;
        }

        public void setParentCity(String parentCity) {
            this.parentCity = parentCity;
        }

        public String getParentCity() {
            return parentCity;
        }

        public void setTz(String tz) {
            this.tz = tz;
        }

        public String getTz() {
            return tz;
        }

    }


    public class Update {

        private String loc;
        private String utc;

        public void setLoc(String loc) {
            this.loc = loc;
        }

        public String getLoc() {
            return loc;
        }

        public void setUtc(String utc) {
            this.utc = utc;
        }

        public String getUtc() {
            return utc;
        }


    }

}
/**
 * Auto-generated: 2018-10-27 19:5:34
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
