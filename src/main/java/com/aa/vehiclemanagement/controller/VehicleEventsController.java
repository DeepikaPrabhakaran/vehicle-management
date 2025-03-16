package com.aa.vehiclemanagement.controller;

import com.aa.vehiclemanagement.dto.VehicleEventsDTO;
import com.aa.vehiclemanagement.entity.VehicleEvents;
import com.aa.vehiclemanagement.service.VehicleEventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/vehicle-events")
public class VehicleEventsController {

    private VehicleEventService vehicleEventService;
    private KafkaProducer kafkaProducer;
    private KafkaConsumer kafkaConsumer;

    public VehicleEventsController(VehicleEventService vehicleEventService, KafkaProducer kafkaProducer, KafkaConsumer kafkaConsumer) {
        this.vehicleEventService = vehicleEventService;
        this.kafkaProducer = kafkaProducer;
        this.kafkaConsumer = kafkaConsumer;
    }

    @GetMapping("/")
    public List<VehicleEventsDTO> getAllVehicleEvents() {
        return vehicleEventService.getAllVehicleEvents();
    }


    @GetMapping("/{vehicle_id}")
    public List<VehicleEventsDTO> getVehicleEventsByVehicleId(@PathVariable Long vehicle_id) {
        return vehicleEventService.getVehicleEventsByVehicleId(vehicle_id);
    }

    @PostMapping("/create")
    public CompletableFuture<VehicleEventsDTO> saveVehicleEvents(@RequestBody VehicleEvents vehicleEvents) {

        CompletableFuture<VehicleEventsDTO> vehicleEventsDTOCompletableFuture = this.kafkaProducer.sendVehicleHealthStatus(vehicleEvents,  null);
        return vehicleEventsDTOCompletableFuture;
    }
}