package com.playground.altimetrik.vindecoder.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@Slf4j
public class ModelService {

    private final RestTemplate restTemplate;

    public ModelService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    String getModelsURL = "https://vpic.nhtsa.dot.gov/api/vehicles/GetModelsForMake/";

    @HystrixCommand(fallbackMethod = "reliable")
    public String callingModelService(String model){
        log.info("ModelService calling started");
        URI modelURI = URI.create(getModelsURL+model+"?format=json");
        return this.restTemplate.getForObject(modelURI, String.class);
    }

    public String reliable(String model) {
        log.error("You failed to hit the model url");
        return "There was an error in dispatching your modelService!! This is a circuit breaker";
    }
}
