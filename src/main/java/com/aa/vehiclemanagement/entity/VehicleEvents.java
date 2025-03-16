package com.aa.vehiclemanagement.entity;

import com.aa.vehiclemanagement.VehicleHealthStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class VehicleEvents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleEventId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    private boolean eventStatus;

    @Enumerated(EnumType.STRING)
    private VehicleHealthStatus vehicleEventHealthStatus;

    private String faultDescription;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();
}