package com.playground.altimetrik.vindecoder.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.playground.altimetrik.vindecoder.model.Make;
import com.playground.altimetrik.vindecoder.model.Model;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MakesService {

    private final RestTemplate restTemplate;

    public MakesService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    String  getMakesURL = "https://vpic.nhtsa.dot.gov/api/vehicles/GetMakesForVehicleType/";

    @HystrixCommand(fallbackMethod = "reliable")
    public String callingMakeService(String make) {
        log.info("Make calling started");
        URI makeuri = URI.create(getMakesURL+make+"?format=json");
        return this.restTemplate.getForObject(makeuri, String.class);

    }

    public String reliable(String make) {
        log.error("You failed to hit the make url");
        return "There was an error in dispatching your makeService!! This is a circuit breaker";
    }
}
