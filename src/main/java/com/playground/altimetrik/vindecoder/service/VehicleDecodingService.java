package com.playground.altimetrik.vindecoder.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import java.net.URI;

@Slf4j
@Service
public class VehicleDecodingService {

    @Value("${DECODING_SERVICE}")
    public String decodingUrl;

    @Value("${MODELS_SERVICE}")
    public String modelsUrl;

    @Value("${MAKES_SERVICE}")
    public String makesUrl;

    private final RestTemplate restTemplate;

    public VehicleDecodingService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "reliable")
    public String callingExternalService(String vin) {
        URI uri = URI.create("http://"+decodingUrl+"/"+vin+"?format=json");
        log.info("I am Decoding guy ");
        return this.restTemplate.getForObject(uri, String.class);
    }

    public String reliable(String vin) {
        log.error("You failed to hit the decoding url");
        return "There was an error in dispatching your decorder!! This is a circuit breaker";
    }


}
