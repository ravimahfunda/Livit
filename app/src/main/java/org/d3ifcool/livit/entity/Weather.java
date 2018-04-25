package org.d3ifcool.livit.entity;

/**
 * Created by Multimedia on 25/04/2018.
 */

public class Weather {
    private String title;
    private String description;
    private double temp;
    private int humidity;

    public Weather() {
        this.title = title;
        this.description = description;
        this.temp = temp;
        this.humidity = humidity;
    }

    public Weather(String title, String description, double temp, int humidity) {
        this.title = title;
        this.description = description;
        this.temp = temp;
        this.humidity = humidity;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getTemp() {
        return temp;
    }

    public int getHumidity() {
        return humidity;
    }
}
