package com.example.administrator.weather.Data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BeanAir {
    public String TableName = "air";

    @SerializedName("air_now_city")
    private AirNowCity airNowCity;
    @SerializedName("air_now_station")
    private List<AirNowStation> airNowStation;
    private Basic basic;
    private String status;
    private Update update;
    public void setAirNowCity(AirNowCity airNowCity) {
        this.airNowCity = airNowCity;
    }
    public AirNowCity getAirNowCity() {
        return airNowCity;
    }

    public void setAirNowStation(List<AirNowStation> airNowStation) {
        this.airNowStation = airNowStation;
    }
    public List<AirNowStation> getAirNowStation() {
        return airNowStation;
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

    public class AirNowCity {

        private String aqi;
        private String co;
        private String main;
        private String no2;
        private String o3;
        private String pm10;
        private String pm25;
        @SerializedName("pub_time")
        private String pubTime;
        private String qlty;
        private String so2;
        public void setAqi(String aqi) {
            this.aqi = aqi;
        }
        public String getAqi() {
            return aqi;
        }

        public void setCo(String co) {
            this.co = co;
        }
        public String getCo() {
            return co;
        }

        public void setMain(String main) {
            this.main = main;
        }
        public String getMain() {
            return main;
        }

        public void setNo2(String no2) {
            this.no2 = no2;
        }
        public String getNo2() {
            return no2;
        }

        public void setO3(String o3) {
            this.o3 = o3;
        }
        public String getO3() {
            return o3;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }
        public String getPm10() {
            return pm10;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }
        public String getPm25() {
            return pm25;
        }

        public void setPubTime(String pubTime) {
            this.pubTime = pubTime;
        }
        public String getPubTime() {
            return pubTime;
        }

        public void setQlty(String qlty) {
            this.qlty = qlty;
        }
        public String getQlty() {
            return qlty;
        }

        public void setSo2(String so2) {
            this.so2 = so2;
        }
        public String getSo2() {
            return so2;
        }

    }
    

    public class AirNowStation {

        @SerializedName("air_sta")
        private String airSta;
        private String aqi;
        private String asid;
        private String co;
        private String lat;
        private String lon;
        private String main;
        private String no2;
        private String o3;
        private String pm10;
        private String pm25;
        @SerializedName("pub_time")
        private String pubTime;
        private String qlty;
        private String so2;
        public void setAirSta(String airSta) {
            this.airSta = airSta;
        }
        public String getAirSta() {
            return airSta;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }
        public String getAqi() {
            return aqi;
        }

        public void setAsid(String asid) {
            this.asid = asid;
        }
        public String getAsid() {
            return asid;
        }

        public void setCo(String co) {
            this.co = co;
        }
        public String getCo() {
            return co;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }
        public String getLat() {
            return lat;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }
        public String getLon() {
            return lon;
        }

        public void setMain(String main) {
            this.main = main;
        }
        public String getMain() {
            return main;
        }

        public void setNo2(String no2) {
            this.no2 = no2;
        }
        public String getNo2() {
            return no2;
        }

        public void setO3(String o3) {
            this.o3 = o3;
        }
        public String getO3() {
            return o3;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }
        public String getPm10() {
            return pm10;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }
        public String getPm25() {
            return pm25;
        }

        public void setPubTime(String pubTime) {
            this.pubTime = pubTime;
        }
        public String getPubTime() {
            return pubTime;
        }

        public void setQlty(String qlty) {
            this.qlty = qlty;
        }
        public String getQlty() {
            return qlty;
        }

        public void setSo2(String so2) {
            this.so2 = so2;
        }
        public String getSo2() {
            return so2;
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
