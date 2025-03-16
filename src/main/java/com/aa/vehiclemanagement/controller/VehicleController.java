package com.aa.vehiclemanagement.controller;
import com.aa.vehiclemanagement.dto.VehicleDTO;
import com.aa.vehiclemanagement.dto.VehicleDetailsDTO;
import com.aa.vehiclemanagement.entity.Vehicle;
import com.aa.vehiclemanagement.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/vehicles")
public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @GetMapping("/")
    public List<VehicleDetailsDTO> getAllVehicleDetails(){
        List<VehicleDetailsDTO> vehicleDetailsDTOList = vehicleService.findAll();
        System.out.println("vehicleDetailsDTOList :: " + vehicleDetailsDTOList);
        return vehicleDetailsDTOList;
    }

    @GetMapping("/id/{id}")
    public Optional<VehicleDetailsDTO> getVehicleDetails(@PathVariable Long id){
    return vehicleService.findByVehicleId(id);
    }

    @GetMapping("/status/{status}")
    public List<VehicleDTO> getByStatus(@PathVariable boolean status){
        return vehicleService.findByStatus(status);
    }

    @PostMapping("/create")
    public ResponseEntity<Vehicle> saveVehicleDetails(@RequestBody Vehicle vehicle){
         Vehicle vehicleDetails = vehicleService.saveVehicleDetails(vehicle);
         return ResponseEntity.ok(vehicleDetails);
    }

    @PatchMapping("/updateStatus/{id}")
    public VehicleDetailsDTO deActivateVehicle(@PathVariable Long id){
        return vehicleService.deactivateVehicle(id);
    }

    @GetMapping("/events/{vehicle_id}")
    public Optional<VehicleDetailsDTO> getVehicleEventsByVehicleId(@PathVariable Long vehicle_id){
        return vehicleService.getVehicleEventsByVehicleId(vehicle_id);
    }
}