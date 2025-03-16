package com.aa.vehiclemanagement.controller;

import com.aa.vehiclemanagement.dto.VehicleEventsDTO;
import com.aa.vehiclemanagement.entity.VehicleEvents;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.concurrent.CompletableFuture;


@Service
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class KafkaProducer {

    private KafkaTemplate<String, VehicleEvents> kafkaTemplate;

    CompletableFuture<VehicleEventsDTO> futureResponse ;

    private static final String TOPIC = "vehicle_event_status";

    public KafkaProducer(KafkaTemplate<String, VehicleEvents> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public CompletableFuture<VehicleEventsDTO> sendVehicleHealthStatus(VehicleEvents vehicleEvents, VehicleEventsDTO vehicleEventsDTO) {
        futureResponse = new CompletableFuture<>();
        kafkaTemplate.send(TOPIC, vehicleEvents);
        return futureResponse;
    }

    public void setResponse(VehicleEventsDTO vehicleEventsDTOResponse){
        if(futureResponse != null)
            futureResponse.complete(vehicleEventsDTOResponse);
    }
}
