package com.sriman.myJournalApp.service;

import com.sriman.myJournalApp.api.response.WeatherResponse;
import com.sriman.myJournalApp.cache.ApplicationCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    public  String apiKey; //3a46452d834c58a4ac6f8aa792fc527e

   // public static final String API="https://api.weatherstack.com/current?access_key=<api_key>&query=<city>";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApplicationCache applicationCache;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city){
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);

        if (weatherResponse != null) {
            return weatherResponse;
        }
        else {
            String finalAPI = applicationCache.appCache.get("weather_api").replace("<city>", city).replace("<api_key>", apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if (body != null) {
                redisService.set("weather_of_" + city, body, 300l);
            }
            return body;
        }
    }
}
