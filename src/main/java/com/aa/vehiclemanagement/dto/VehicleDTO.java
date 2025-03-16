package com.aa.vehiclemanagement.dto;

import com.aa.vehiclemanagement.VehicleHealthStatus;
import com.aa.vehiclemanagement.entity.VehicleEvents;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleDTO {

        private Long vehicleId;
        private String name;
        private String model;
        private String vrn;
        private boolean vehicleStatus;
        private VehicleHealthStatus vehicleHealthStatus;
        private int numberOfFaults;
}