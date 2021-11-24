package me.aditya.weather.database;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Seed {
    @JsonProperty
    private String host;
    @JsonProperty
    private String port;
}
