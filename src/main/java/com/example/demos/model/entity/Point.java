package com.example.demos.model.entity;

public class Point {
    private String longitude;
    private String latitude;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public Point(){

    }

    public Point(String latitude,String longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
