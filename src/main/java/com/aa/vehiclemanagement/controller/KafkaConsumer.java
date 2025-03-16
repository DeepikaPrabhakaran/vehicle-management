package com.aa.vehiclemanagement.controller;

import com.aa.vehiclemanagement.dto.VehicleEventsDTO;
import com.aa.vehiclemanagement.entity.VehicleEvents;
import com.aa.vehiclemanagement.service.VehicleEventService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class KafkaConsumer {

    private VehicleEventService vehicleEventService;
    private KafkaProducer kafkaProducer;

    private KafkaConsumer(VehicleEventService vehicleEventService, KafkaProducer kafkaProducer){
        this.kafkaProducer = kafkaProducer;
        this.vehicleEventService = vehicleEventService;
    }

    @KafkaListener(topics = "vehicle_event_status", groupId = "vehicle-group")
    public void consumeHealthStatus(VehicleEvents vehicleEvents) {

        vehicleEventService.saveVehicleEvent(vehicleEvents);

        VehicleEventsDTO vehicleEventsDTO = new VehicleEventsDTO();

        vehicleEventsDTO.setVehicleEventId(vehicleEvents.getVehicleEventId());
        vehicleEventsDTO.setVehicleId(vehicleEvents.getVehicle().getId());
        vehicleEventsDTO.setEventStatus(vehicleEvents.isEventStatus());
        vehicleEventsDTO.setVehicleHealthStatus(vehicleEvents.getVehicleEventHealthStatus());
        vehicleEventsDTO.setFaultDescription(vehicleEvents.getFaultDescription());
        vehicleEventsDTO.setLocalDateTime(vehicleEvents.getCreatedAt());

        kafkaProducer.setResponse(vehicleEventsDTO);

    }
}
