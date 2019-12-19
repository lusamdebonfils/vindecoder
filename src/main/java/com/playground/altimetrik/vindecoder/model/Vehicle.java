package com.playground.altimetrik.vindecoder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Vehicle {
    private Make vehicleMake;
    private String vehicleVIN;
    private Model vehicleModel;
    private String vehicleYear;
    private String vehiclePlantCountry;
    private String vehiclePlantState;
    private VehicleType vehicleType;

}
