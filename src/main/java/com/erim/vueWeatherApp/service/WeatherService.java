package com.erim.vueWeatherApp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;


@Service
public class WeatherService {
    private static final String OPEN_WEATHER_API_KEY ="0c4070faae1ba2d0970db43f9bda45a4";
    //URL
    private static final String OPEN_WEATHER_URL ="api.openweathermap.org/data/2.5/weather?q=";
    private RestTemplate restTemplate;



    /**
     * Get the weather for a specific City.
     *
     * @param cityName The city name(in ENGLISH)
     * @return the weather data
     */
    public String getWeatherForCity(String cityName) throws URISyntaxException, IOException, InterruptedException {

        //api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
        String uri = "https://api.openweathermap.org/data/2.5/weather?q="+cityName+"&appid="+OPEN_WEATHER_API_KEY+ "&units=metric";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .header("Accept","application/json")
                .GET()
                .build();


        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        System.out.println(response.body());
        return response.body();

    }
}
