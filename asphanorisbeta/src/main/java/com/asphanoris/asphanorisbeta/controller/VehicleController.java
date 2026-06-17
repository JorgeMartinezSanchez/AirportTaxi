package com.asphanoris.asphanorisbeta.controller;

import com.asphanoris.asphanorisbeta.dto.VehicleRequestDTO;
import com.asphanoris.asphanorisbeta.dto.VehicleResponseDTO;
import com.asphanoris.asphanorisbeta.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    
    @Autowired
    private VehicleService vehicleService;
    
    @PostMapping
    public ResponseEntity<VehicleResponseDTO> createVehicle(@RequestBody VehicleRequestDTO vehicleDto) {
        return ResponseEntity.ok(vehicleService.createVehicle(vehicleDto));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> modifyVehicle(
            @PathVariable Long id,
            @RequestBody VehicleRequestDTO vehicleDto) {
        // Pasas el ID de la URL y el DTO del body
        return ResponseEntity.ok(vehicleService.modifyVehicle(id, vehicleDto));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> getVehicle(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.getVehicle(id));
    }
    
    @GetMapping
    public ResponseEntity<List<VehicleResponseDTO>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }
    
    @PatchMapping("/{id}/km")
    public ResponseEntity<VehicleResponseDTO> updateKm(
            @PathVariable Long id,
            @RequestParam Double km) {
        return ResponseEntity.ok(vehicleService.updateKm(id, km));
    }
}