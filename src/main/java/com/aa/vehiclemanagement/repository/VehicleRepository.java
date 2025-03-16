package com.aa.vehiclemanagement.repository;

import com.aa.vehiclemanagement.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByVehicleStatus(boolean status);
}
