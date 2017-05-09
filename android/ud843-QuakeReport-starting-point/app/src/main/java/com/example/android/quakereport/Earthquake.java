package com.example.android.quakereport;

/**
 * Created by eribas on 08/05/2017.
 */

public class Earthquake {
    private double magnitude;
    private String location, url;
    private long timeInMilliseconds;

    public Earthquake(double magnitude, String location, long timeInMilliseconds, String url){
        this.magnitude = magnitude;
        this.location = location;
        this.timeInMilliseconds = timeInMilliseconds;
        this.url = url;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public String getUrl() {
        return url;
    }

    public long getTimeInMilliseconds() {
        return timeInMilliseconds;
    }
}
