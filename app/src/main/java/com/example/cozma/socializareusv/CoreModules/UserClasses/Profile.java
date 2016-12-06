package com.example.cozma.socializareusv.CoreModules.UserClasses;

/**
 * Created by cozma on 02.12.2016.
 */

public class Profile {

    private Double latitude = 0d;

    private Double longitude = 0d;
    private Integer uid = 0;

    public Profile(){

    }

    public Profile(String string){
        this.latitude = null;
        this.longitude = null;
        this.uid = null;
    }


    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
