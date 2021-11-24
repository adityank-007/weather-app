package me.aditya.weather.api;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import me.aditya.weather.model.WeatherInfo;

import java.util.List;

@Slf4j
@Data
@AllArgsConstructor
public class WeatherResponse {
    private String currentTemperature;
    List<WeatherInfo> weatherInfoList;
}
