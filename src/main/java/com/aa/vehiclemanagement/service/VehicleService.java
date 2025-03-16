package com.aa.vehiclemanagement.service;

import com.aa.vehiclemanagement.dto.VehicleDTO;
import com.aa.vehiclemanagement.dto.VehicleDetailsDTO;
import com.aa.vehiclemanagement.dto.VehicleEventsDTO;
import com.aa.vehiclemanagement.entity.Vehicle;
import com.aa.vehiclemanagement.entity.VehicleEvents;
import com.aa.vehiclemanagement.exception.VehicleNotFoundException;
import com.aa.vehiclemanagement.repository.VehicleEventsRepository;
import com.aa.vehiclemanagement.repository.VehicleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleEventsRepository vehicleEventsRepository;

    public VehicleService(VehicleRepository vehicleRepository, VehicleEventsRepository vehicleEventsRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleEventsRepository = vehicleEventsRepository;
    }

    public Vehicle saveVehicleDetails(Vehicle vehicleDetails) {
        return vehicleRepository.save(vehicleDetails);
    }

    public Optional<VehicleDetailsDTO> findByVehicleId(Long vehicleId) {
        return vehicleRepository.findById(vehicleId)
                .map(getVehicleDetailsDTOFunction());
    }


    public Optional<VehicleDetailsDTO> getVehicleEventsByVehicleId(Long vehicleId) {
        return vehicleRepository.findById(vehicleId)
                .map(getVehicleDetailsDTOFunction());
    }

    @Transactional
    public VehicleDetailsDTO deactivateVehicle(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() ->
                        new VehicleNotFoundException("Vehicle not found to deactivate"));

        System.out.println("Vehicle STATUS : "+ vehicle.isVehicleStatus());
        if (vehicle.isVehicleStatus())
            vehicle.setVehicleStatus(false);
        vehicleRepository.save(vehicle);

        List<VehicleEvents> vehicleEventsList =
                vehicleEventsRepository.findByVehicleId(vehicleId)
                        .stream()
                        .filter(vehicleEvent -> vehicleEvent.isEventStatus())
                        .peek(event -> event.setEventStatus(false))
                        .collect(Collectors.toList());
        vehicleEventsRepository.saveAll(vehicleEventsList);

        List<VehicleEventsDTO> vehicleEventsDTOList =
                vehicleEventsList.stream().map(vehicleEvent ->
        {
           return new VehicleEventsDTO(
                vehicleEvent.getVehicleEventId(),
                vehicleEvent.getVehicle().getId(),
                vehicleEvent.isEventStatus(),
                vehicleEvent.getVehicleEventHealthStatus(),
                vehicleEvent.getFaultDescription(),
                vehicleEvent.getCreatedAt());
        }).collect(Collectors.toList());

        return new VehicleDetailsDTO(
                vehicle.getId(),
                vehicle.getName(),
                vehicle.getModel(),
                vehicle.getVrn(),
                vehicle.isVehicleStatus(),
                vehicle.getVehicleHealthStatus(),
                vehicleEventsDTOList != null ? vehicleEventsDTOList : null,
                vehicle.getNumberOfFaults());

    }

    public List<VehicleDTO> findByStatus(boolean status) {

        return vehicleRepository.findByVehicleStatus(status).stream()
                .map(vehicle -> {
                    return new VehicleDTO(
                            vehicle.getId(),
                            vehicle.getName(),
                            vehicle.getModel(),
                            vehicle.getVrn(),
                            vehicle.isVehicleStatus(),
                            vehicle.getVehicleHealthStatus(),
                            vehicle.getNumberOfFaults());
                }).collect(Collectors.toList());
    }

    public List<VehicleDetailsDTO> findAll() {
        return vehicleRepository.findAll().stream().map(getVehicleDetailsDTOFunction()).toList();
    }

    private static Function<Vehicle, VehicleDetailsDTO> getVehicleDetailsDTOFunction() {
        return vehicle -> {
            List<VehicleEventsDTO> vehicleEventsDTOList = vehicle.getVehicleEvents().stream()
                    .filter(vehicleEvent -> vehicleEvent.isEventStatus())
                    .map(vehicleEvent -> new VehicleEventsDTO(
                            vehicleEvent.getVehicleEventId(),
                            vehicleEvent.getVehicle().getId(),
                            vehicleEvent.isEventStatus(),
                            vehicleEvent.getVehicleEventHealthStatus(),
                            vehicleEvent.getFaultDescription(),
                            vehicleEvent.getCreatedAt())).collect(Collectors.toList());

            return new VehicleDetailsDTO(
                    vehicle.getId(),
                    vehicle.getName(),
                    vehicle.getModel(),
                    vehicle.getVrn(),
                    vehicle.isVehicleStatus(),
                    vehicle.getVehicleHealthStatus(),
                    vehicleEventsDTOList,
                    vehicle.getNumberOfFaults());
        };
    }
}
