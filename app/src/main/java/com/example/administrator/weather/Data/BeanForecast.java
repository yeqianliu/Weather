package com.example.administrator.weather.Data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BeanForecast {
    public String TableName = "forecast";

    @SerializedName("daily_forecast")
    private List<DailyForecast> dailyForecast;
    private Basic basic;
    private String status;
    private Update update;
    public void setDailyForecast(List<DailyForecast> dailyForecast) {
        this.dailyForecast = dailyForecast;
    }
    public List<DailyForecast> getDailyForecast() {
        return dailyForecast;
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

    public class DailyForecast {

        @SerializedName("cond_code_d")
        private String condCodeD;
        @SerializedName("cond_code_n")
        private String condCodeN;
        @SerializedName("cond_txt_d")
        private String condTxtD;
        @SerializedName("cond_txt_n")
        private String condTxtN;
        private String date;
        private String hum;
        private String mr;
        private String ms;
        private String pcpn;
        private String pop;
        private String pres;
        private String sr;
        private String ss;
        @SerializedName("tmp_max")
        private String tmpMax;
        @SerializedName("tmp_min")
        private String tmpMin;
        @SerializedName("uv_index")
        private String uvIndex;
        private String vis;
        @SerializedName("wind_deg")
        private String windDeg;
        @SerializedName("wind_dir")
        private String windDir;
        @SerializedName("wind_sc")
        private String windSc;
        @SerializedName("wind_spd")
        private String windSpd;
        public void setCondCodeD(String condCodeD) {
            this.condCodeD = condCodeD;
        }
        public String getCondCodeD() {
            return condCodeD;
        }

        public void setCondCodeN(String condCodeN) {
            this.condCodeN = condCodeN;
        }
        public String getCondCodeN() {
            return condCodeN;
        }

        public void setCondTxtD(String condTxtD) {
            this.condTxtD = condTxtD;
        }
        public String getCondTxtD() {
            return condTxtD;
        }

        public void setCondTxtN(String condTxtN) {
            this.condTxtN = condTxtN;
        }
        public String getCondTxtN() {
            return condTxtN;
        }

        public void setDate(String date) {
            this.date = date;
        }
        public String getDate() {
            return date;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }
        public String getHum() {
            return hum;
        }

        public void setMr(String mr) {
            this.mr = mr;
        }
        public String getMr() {
            return mr;
        }

        public void setMs(String ms) {
            this.ms = ms;
        }
        public String getMs() {
            return ms;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
        }
        public String getPcpn() {
            return pcpn;
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

        public void setSr(String sr) {
            this.sr = sr;
        }
        public String getSr() {
            return sr;
        }

        public void setSs(String ss) {
            this.ss = ss;
        }
        public String getSs() {
            return ss;
        }

        public void setTmpMax(String tmpMax) {
            this.tmpMax = tmpMax;
        }
        public String getTmpMax() {
            return tmpMax;
        }

        public void setTmpMin(String tmpMin) {
            this.tmpMin = tmpMin;
        }
        public String getTmpMin() {
            return tmpMin;
        }

        public void setUvIndex(String uvIndex) {
            this.uvIndex = uvIndex;
        }
        public String getUvIndex() {
            return uvIndex;
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
