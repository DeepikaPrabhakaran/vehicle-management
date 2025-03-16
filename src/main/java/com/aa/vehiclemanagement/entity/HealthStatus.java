package com.aa.vehiclemanagement.entity;

import com.aa.vehiclemanagement.VehicleHealthStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthStatus {

    private Long vehicleId;
    private VehicleHealthStatus status;
    private String faultDescription;

}
