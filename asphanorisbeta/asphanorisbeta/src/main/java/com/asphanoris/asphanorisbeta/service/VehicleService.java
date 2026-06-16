package com.asphanoris.asphanorisbeta.service;

import com.asphanoris.asphanorisbeta.builder.VehicleBuilder;
import com.asphanoris.asphanorisbeta.domain.ConVehicle;
import com.asphanoris.asphanorisbeta.dto.VehicleRequestDTO;
import com.asphanoris.asphanorisbeta.dto.VehicleResponseDTO;
import com.asphanoris.asphanorisbeta.factory.VehicleFactory;
import com.asphanoris.asphanorisbeta.repository.IVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    
    @Autowired
    private IVehicleRepository vehicleRepo;
    
    private VehicleFactory vehicleFactory = new VehicleFactory() {
        @Override
        public ConVehicle createVehicle(VehicleRequestDTO newVehicle) {
            return new VehicleBuilder()
                .setType(newVehicle.getType())
                .setPlate(newVehicle.getPlate())
                .setPeopleCapacity(newVehicle.getPeopleCapacity())
                .setCargoSize(newVehicle.getCargoSize())
                .setCurrentKm(newVehicle.getCurrentKm())
                .build();
        }
    };
    
    private VehicleResponseDTO convertToDTO(ConVehicle vehicle) {
        VehicleResponseDTO dto = new VehicleResponseDTO();
        dto.setId(vehicle.getId());
        dto.setType(vehicle.getType());
        dto.setPlate(vehicle.getPlate());
        dto.setPeopleCapacity(vehicle.getPeopleCapacity());
        dto.setCargoSize(vehicle.getCargoSize());
        dto.setCurrentKm(vehicle.getCurrentKm());
        return dto;
    }
    
    public VehicleResponseDTO createVehicle(VehicleRequestDTO vehicleDto) {
        ConVehicle vehicle = vehicleFactory.createVehicle(vehicleDto);
        ConVehicle saved = vehicleRepo.addVehicle(vehicle);
        return convertToDTO(saved);
    }
    
    public VehicleResponseDTO modifyVehicle(Long vehicleId, VehicleRequestDTO vehicleDto) {
        ConVehicle existing = vehicleRepo.getVehicle(vehicleId);
        if (existing != null) {
            if (vehicleDto.getType() != null) existing.setType(vehicleDto.getType());
            if (vehicleDto.getPlate() != null) existing.setPlate(vehicleDto.getPlate());
            if (vehicleDto.getPeopleCapacity() != null) existing.setPeopleCapacity(vehicleDto.getPeopleCapacity());
            if (vehicleDto.getCargoSize() != null) existing.setCargoSize(vehicleDto.getCargoSize());
            if (vehicleDto.getCurrentKm() != null) existing.setCurrentKm(vehicleDto.getCurrentKm());
            
            ConVehicle updated = vehicleRepo.modifyVehicle(existing);
            return convertToDTO(updated);
        }
        throw new RuntimeException("Vehicle not found with id: " + vehicleId);
    }
    
    public void deleteVehicle(Long vehicleId) {
        ConVehicle vehicle = vehicleRepo.getVehicle(vehicleId);
        if (vehicle != null) {
            vehicleRepo.deleteVehicle(vehicle);
        } else {
            throw new RuntimeException("Vehicle not found with id: " + vehicleId);
        }
    }
    
    public VehicleResponseDTO getVehicle(Long vehicleId) {
        ConVehicle vehicle = vehicleRepo.getVehicle(vehicleId);
        if (vehicle != null) {
            return convertToDTO(vehicle);
        }
        throw new RuntimeException("Vehicle not found with id: " + vehicleId);
    }
    
    public List<VehicleResponseDTO> getAllVehicles() {
        return vehicleRepo.getAllVehicles().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    public VehicleResponseDTO updateKm(Long vehicleId, Double newKm) {
        ConVehicle vehicle = vehicleRepo.getVehicle(vehicleId);
        if (vehicle != null) {
            vehicle.updateKm(newKm);
            ConVehicle updated = vehicleRepo.modifyVehicle(vehicle);
            return convertToDTO(updated);
        }
        throw new RuntimeException("Vehicle not found with id: " + vehicleId);
    }
}