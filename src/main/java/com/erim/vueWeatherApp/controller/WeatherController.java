package com.erim.vueWeatherApp.controller;

import com.erim.vueWeatherApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;


@RestController
@RequestMapping(value = "/api")
@CrossOrigin
public class WeatherController {

    @Autowired
    private WeatherService weatherService;
    @RequestMapping(value = "/getWeather/{city}")
    public String getWeatherForCity(@PathVariable String city) throws URISyntaxException, IOException, InterruptedException {
        System.out.println(city);
        return weatherService.getWeatherForCity(city);

    }
    @CrossOrigin
    @RequestMapping(value = "/hello")
    public String getWeatherForCity() throws URISyntaxException, IOException, InterruptedException {
        System.out.println("frontend call!");
        return "helloooo";


    }
}
