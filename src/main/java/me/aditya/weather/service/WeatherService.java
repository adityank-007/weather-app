package me.aditya.weather.service;


import lombok.extern.slf4j.Slf4j;
import me.aditya.weather.api.WeatherResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import javax.ws.rs.BadRequestException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Slf4j
public class WeatherService {
    private static final String API_SOURCE = "http://api.weatherapi.com/v1/forecast.json";
    public WeatherResponse getWeatherData(String location, String forecastDays) throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String temperature = null;
        try {
            //?key=4ea89488542041b1ac9172920212211&q=London&days=1&aqi=no&alerts=no
            HttpGet request = new HttpGet(API_SOURCE);
            URI getUri = new URIBuilder(request.getURI())
                    .addParameter("q", location)
                    .addParameter("key", "4ea89488542041b1ac9172920212211")
                    .addParameter("days", forecastDays)
                    .addParameter("aqi","no")
                    .addParameter("alerts","no")
                    .build();
            request.setURI(getUri);
            CloseableHttpResponse response = httpClient.execute(request);
            if(response.getStatusLine().getStatusCode() == 400)
            {
                throw new BadRequestException("Location Not Found");
            }
            String json = null;
            try {

                // Get HttpResponse Status
                System.out.println(response.getStatusLine().toString());// HTTP/1.1 200 OK

                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    json = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                }

            } finally {
                response.close();
            }

            assert json != null;
            JSONObject jsonObject = new JSONObject(json);
            log.info("Response from server : {}",jsonObject.toString());
            double currentTemperature  = jsonObject.getJSONObject("current").getDouble("temp_f");
            temperature = String.valueOf(currentTemperature);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        return new WeatherResponse(temperature, new ArrayList<>());

    }

}
