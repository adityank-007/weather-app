package me.aditya.weather.model;

import lombok.Data;

@Data
public class WeatherInfo {
    private String date;
    private String highTemperature;
    private String lowTemperature;
}
