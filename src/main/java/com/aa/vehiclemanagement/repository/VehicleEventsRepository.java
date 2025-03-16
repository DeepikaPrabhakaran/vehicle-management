package com.aa.vehiclemanagement.repository;

import com.aa.vehiclemanagement.entity.VehicleEvents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleEventsRepository extends JpaRepository<VehicleEvents, Long> {

    List<VehicleEvents> findByVehicleId(Long vehicleId);
}
