package com.playground.altimetrik.vindecoder.controller;

import com.playground.altimetrik.vindecoder.model.Make;
import com.playground.altimetrik.vindecoder.model.Model;
import com.playground.altimetrik.vindecoder.model.Vehicle;
import com.playground.altimetrik.vindecoder.service.MakesService;
import com.playground.altimetrik.vindecoder.service.ModelService;
import com.playground.altimetrik.vindecoder.service.VehicleDecodingService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@EnableCircuitBreaker
public class VinDecoderController {

    @Autowired
    private VehicleDecodingService vehicleDecodingService;

    @Autowired
    private MakesService makesService;

    @Autowired
    private ModelService modelService;

    List<Model> models = new ArrayList<>();
    List<Make> makes = new ArrayList<>();
    List<Vehicle> vehicles = new ArrayList<>();

    @Cacheable("vehicles")
    @CrossOrigin
    @GetMapping(value = "/api/vindecoder/service/{vin}", produces = "application/json")
    public List<Vehicle> processVin(@PathVariable String vin){
        log.info("Passed in VIN is : "+vin);
        //Pass VIN and Decode
        String decodedData = callDecodingServ(vin);
        JSONObject decodedJSON = new JSONObject(decodedData);
        decodedJSON = decodedJSON.getJSONArray("Results").getJSONObject(0);

        String vehicleVIN = decodedJSON.getString("VIN");
        String vehicleMake = decodedJSON.getString("Make");
        String vehicleModel = decodedJSON.getString("Model");
        String vehicleYear = decodedJSON.getString("ModelYear");
        String vehiclePlantCountry = decodedJSON.getString("PlantCountry");
        String vehiclePlantState = decodedJSON.getString("PlantState");
        String vehicleType = decodedJSON.getString("VehicleType");

        //calling makes service
        String makesData = callMakeServ(vehicleMake);
        JSONObject makesJSON = new JSONObject(makesData);
        JSONArray makesArray = makesJSON.getJSONArray("Results");

        for(int i = 0; i< makesArray.length(); i++){

            JSONObject jsonObject = new JSONObject();
            jsonObject = makesArray.getJSONObject(i);
            Make make = new Make();
            make.setMakeID(jsonObject.getInt("MakeId"));
            make.setMakeName(jsonObject.getString("MakeName"));
            make.setVehicleTypeID(jsonObject.getInt("VehicleTypeId"));
            make.setVehicleTypeName(jsonObject.getString("VehicleTypeName"));
            makes.add(make);
        }

        //Models service calling
        String modelsData = callModelServ(vehicleModel);
        JSONObject modelsJSON = new JSONObject(modelsData);
        JSONArray modelsArray = modelsJSON.getJSONArray("Results");

        for(int i = 0; i< modelsArray.length(); i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject = modelsArray.getJSONObject(i);
            Model model = new Model();
            model.setMakeID(jsonObject.getInt("Make_ID"));
            model.setMakeName(jsonObject.getString("Make_Name"));
            model.setModelID(jsonObject.getInt("Model_ID"));
            model.setModelName(jsonObject.getString("Model_Name"));
            models.add(model);
        }
        /*
        Create Vehicle Object:
         */

        Vehicle vehicle = new Vehicle();

        vehicle.setVehicleVIN(vehicleVIN);
        vehicle.setVehicleMake(vehicleMake);
        vehicle.setVehicleModel(vehicleModel);
        vehicle.setVehicleYear(vehicleYear);
        vehicle.setVehiclePlantCountry(vehiclePlantCountry);
        vehicle.setVehiclePlantState(vehiclePlantState);
        vehicle.setVehicleType(vehicleType);

        vehicle.setVehicleMakes(makes);
        vehicle.setVehicleModels(models);
        vehicles.add(vehicle);
        log.info("vehicles : " + vehicles);
        return vehicles;

    }

    private String callDecodingServ(String vin){
        return  vehicleDecodingService.callingDecodingService(vin);
    }

    private String callModelServ(String model){
        return  modelService.callingModelService(model);
    }

    private String callMakeServ(String make){
        return  makesService.callingMakeService(make);
    }



}
