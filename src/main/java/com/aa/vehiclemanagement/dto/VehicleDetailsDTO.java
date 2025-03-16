package com.aa.vehiclemanagement.dto;

import com.aa.vehiclemanagement.VehicleHealthStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleDetailsDTO {

        private Long vehicleId;
        private String name;
        private String model;
        private String vrn;
        private boolean vehicleStatus;
        private VehicleHealthStatus vehicleHealthStatus;
        private List<VehicleEventsDTO> vehicleEventsList;
        private int numberOfFaults;



}
