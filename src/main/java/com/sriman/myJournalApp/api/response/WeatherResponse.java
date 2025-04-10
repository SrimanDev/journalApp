package com.sriman.myJournalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherResponse {

    private Current current;

    @Data
    public class Current{

        private int temperature;

        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;

        @JsonProperty("feelslike")
        private String feelsLike;
    }
}
