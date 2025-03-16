package com.aa.vehiclemanagement.controller;

import com.aa.vehiclemanagement.VehicleHealthStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/vehicle-health")
public class VehicleHealthStatusEnumController {

    @GetMapping("/healthStatusList")
    public List<VehicleHealthStatus> getVehicleHealthStatusList(){
        return new ArrayList<>(Arrays.asList(VehicleHealthStatus.values()));
    }
}
