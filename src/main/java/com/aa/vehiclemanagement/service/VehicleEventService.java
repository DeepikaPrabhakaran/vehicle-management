package com.aa.vehiclemanagement.service;

import com.aa.vehiclemanagement.controller.KafkaProducer;
import com.aa.vehiclemanagement.dto.VehicleEventsDTO;
import com.aa.vehiclemanagement.entity.VehicleEvents;
import com.aa.vehiclemanagement.exception.VehicleNotFoundException;
import com.aa.vehiclemanagement.repository.VehicleEventsRepository;

import com.aa.vehiclemanagement.repository.VehicleRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleEventService {

    private VehicleEventsRepository vehicleEventsRepository;
    private VehicleRepository vehicleRepository;
    private KafkaProducer kafkaProducer;

    public VehicleEventService(VehicleEventsRepository vehicleEventsRepository, VehicleRepository vehicleRepository, KafkaProducer kafkaProducer) {
        this.vehicleEventsRepository = vehicleEventsRepository;
        this.vehicleRepository = vehicleRepository;
        this.kafkaProducer = kafkaProducer;
    }

    public List<VehicleEventsDTO> getAllVehicleEvents(){
        return vehicleEventsRepository.findAll().stream()
                .map(vehicleEvent ->
                    new VehicleEventsDTO(
                            vehicleEvent.getVehicleEventId(),
                            vehicleEvent.getVehicle().getId(),
                            vehicleEvent.isEventStatus(),
                            vehicleEvent.getVehicleEventHealthStatus(),
                            vehicleEvent.getFaultDescription(),
                            vehicleEvent.getCreatedAt()))
                .collect(Collectors.toList());
    }

    public List<VehicleEventsDTO> getVehicleEventsByVehicleId(Long vehicle_id){
        return vehicleEventsRepository.findByVehicleId(vehicle_id).stream()
                .filter(vehicleEvent -> vehicleEvent.isEventStatus())
                .map(vehicleEvent ->
                        new VehicleEventsDTO(
                                vehicleEvent.getVehicleEventId(),
                                vehicleEvent.getVehicle().getId(),
                                vehicleEvent.isEventStatus(),
                                vehicleEvent.getVehicleEventHealthStatus(),
                                vehicleEvent.getFaultDescription(),
                                vehicleEvent.getCreatedAt()))
                .collect(Collectors.toList());
    }

    public Optional<VehicleEventsDTO> saveVehicleEvent(VehicleEvents vehicleEvent) {

       return vehicleRepository.findById(vehicleEvent.getVehicle().getId())
               .map(existingVehicle -> {
                   existingVehicle.setVehicleHealthStatus(vehicleEvent.getVehicleEventHealthStatus());
                   existingVehicle.setNumberOfFaults(Integer.valueOf(existingVehicle.getNumberOfFaults())+1);
                   vehicleRepository.save(existingVehicle);
                   vehicleEvent.setVehicle(existingVehicle);
                   vehicleEvent.setEventStatus(true);
                   return Optional.ofNullable(vehicleEventsRepository.save(vehicleEvent))
                           .map(newVehicleEvent -> {

                               return new  VehicleEventsDTO(
                               newVehicleEvent.getVehicleEventId(),
                               newVehicleEvent.getVehicle().getId(),
                               newVehicleEvent.isEventStatus(),
                               newVehicleEvent.getVehicleEventHealthStatus(),
                               newVehicleEvent.getFaultDescription(),
                               newVehicleEvent.getCreatedAt());
                           });
               })
                .orElseThrow(() -> {
                    return new VehicleNotFoundException("Vehicle " + vehicleEvent.getVehicle().getId() + " not found");
                });
    }
}