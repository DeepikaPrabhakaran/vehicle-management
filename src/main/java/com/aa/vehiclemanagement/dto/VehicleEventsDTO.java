package com.aa.vehiclemanagement.dto;

import com.aa.vehiclemanagement.VehicleHealthStatus;
import com.aa.vehiclemanagement.entity.Vehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class VehicleEventsDTO {

    private Long vehicleEventId;
    private Long vehicleId;
    private boolean eventStatus;
    private VehicleHealthStatus vehicleHealthStatus;
    private String faultDescription;
    private LocalDateTime localDateTime;

}
