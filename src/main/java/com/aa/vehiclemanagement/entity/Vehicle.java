package com.aa.vehiclemanagement.entity;

import com.aa.vehiclemanagement.VehicleHealthStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    private String model;

    private String vrn;

    @Enumerated(EnumType.STRING)
    private VehicleHealthStatus vehicleHealthStatus;

    private boolean vehicleStatus;

    private Integer numberOfFaults;

   @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   private List<VehicleEvents> vehicleEvents;

   @PrePersist
    public void setDefaults(){

       vehicleStatus = true;
       if(vehicleEvents == null) {
           numberOfFaults = 0;
       }
   }
   public Integer getNumberOfFaults(){
       return vehicleEvents != null ? vehicleEvents.size() : 0;
   }
}


