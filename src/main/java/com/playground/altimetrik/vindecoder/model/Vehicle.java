package com.playground.altimetrik.vindecoder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Vehicle {
    private String vehicleVIN;
    private String vehicleMake;
    private String vehicleModel;
    private String vehicleYear;
    private String vehiclePlantCountry;
    private String vehiclePlantState;
    private String vehicleType;
    private String vehiclePlantCity;
    private List<Model> vehicleModels;
    private List<Make> vehicleMakes;
}
