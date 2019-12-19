package com.playground.altimetrik.vindecoder.controller;

import com.playground.altimetrik.vindecoder.service.VehicleDecodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableCircuitBreaker
public class VinDecoderController {

    @Autowired
    private VehicleDecodingService vehicleDecodingService;

    @GetMapping("/api/vindecoder/service/{vin}")
    public String processVin(@PathVariable String vin) {
        return vehicleDecodingService.callingExternalService(vin);
    }




}
