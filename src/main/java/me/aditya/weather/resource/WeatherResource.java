package me.aditya.weather.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.aditya.weather.api.WeatherRequest;
import me.aditya.weather.api.WeatherResponse;
import me.aditya.weather.service.UserService;
import me.aditya.weather.service.WeatherService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URISyntaxException;

@Slf4j
@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class WeatherResource {
    private static final ObjectMapper MAPPER = Jackson.newMinimalObjectMapper();
    private final UserService userService;
    private final WeatherService weatherService;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/weather-data/{location}")
    public Response weatherForecast(@PathParam("location") String location) throws URISyntaxException, IOException {
        WeatherRequest weatherRequest = new WeatherRequest(location);
        log.info("Calling Weather service with request : {}", weatherRequest);
        WeatherResponse weatherResponse = weatherService.getWeatherData(weatherRequest.getLocation(),"1");
        log.info("Response from weather service : {}", weatherService);
        return Response.ok().entity(weatherResponse).build();
    }
}
