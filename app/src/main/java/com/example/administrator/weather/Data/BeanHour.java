package com.example.administrator.weather.Data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BeanHour {
    public String TableName = "hour";

    private List<Hourly> hourly;
    private Basic basic;
    private String status;
    private Update update;
    public void setHourly(List<Hourly> hourly) {
        this.hourly = hourly;
    }
    public List<Hourly> getHourly() {
        return hourly;
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

    public class Hourly {

        private String cloud;
        @SerializedName("cond_code")
        private String condCode;
        @SerializedName("cond_txt")
        private String condTxt;
        private String dew;
        private String hum;
        private String pop;
        private String pres;
        private String time;
        private String tmp;
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

        public void setDew(String dew) {
            this.dew = dew;
        }
        public String getDew() {
            return dew;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }
        public String getHum() {
            return hum;
        }

        public void setPop(String pop) {
            this.pop = pop;
        }
        public String getPop() {
            return pop;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }
        public String getPres() {
            return pres;
        }

        public void setTime(String time) {
            this.time = time;
        }
        public String getTime() {
            return time;
        }

        public void setTmp(String tmp) {
            this.tmp = tmp;
        }
        public String getTmp() {
            return tmp;
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
