package com.playground.altimetrik.vindecoder.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.playground.altimetrik.vindecoder.model.Make;
import com.playground.altimetrik.vindecoder.model.Model;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class VehicleDecodingService {

    @Value("${DECODING_SERVICE}")
    public String decodingUrl;

    @Value("${MODELS_SERVICE}")
    public String modelsUrl;

    @Value("${MAKES_SERVICE}")
    public String makesUrl;

    String decodeURL = "https://vpic.nhtsa.dot.gov/api/vehicles/decodevinvalues/";

    private final RestTemplate restTemplate;

    public VehicleDecodingService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "reliable")
    public String callingDecodingService(String vin){
        log.info("Decoding started");
        URI decodeuri = URI.create(decodeURL+vin+"?format=json");
        return this.restTemplate.getForObject(decodeuri, String.class);
    }

    public String reliable(String vin) {
        log.error("You failed to hit the decoding url");
        return "There was an error in dispatching your decorder!! This is a circuit breaker";
    }


}
