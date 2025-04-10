package com.sriman.myJournalApp.controllers;

import com.sriman.myJournalApp.api.response.WeatherResponse;
import com.sriman.myJournalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("weather")
public class WeatherController {
    
    @Autowired
    private WeatherService weatherService;
    
    @GetMapping("/{city}")
    public ResponseEntity<String> getWeather(@PathVariable String city){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        WeatherResponse weatherResponse = weatherService.getWeather(city);
        String weatherFeelsLike = weatherResponse.getCurrent().getFeelsLike();
        
        return new ResponseEntity<>("Hi "+ userName+ " weather feels like " +weatherFeelsLike, HttpStatus.OK);
    }
}
