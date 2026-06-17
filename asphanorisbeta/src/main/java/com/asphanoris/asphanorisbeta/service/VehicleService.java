package com.asphanoris.asphanorisbeta.service;

import com.asphanoris.asphanorisbeta.builder.VehicleBuilder;
import com.asphanoris.asphanorisbeta.domain.ConVehicle;
import com.asphanoris.asphanorisbeta.dto.VehicleRequestDTO;
import com.asphanoris.asphanorisbeta.dto.VehicleResponseDTO;
import com.asphanoris.asphanorisbeta.factory.VehicleFactory;
import com.asphanoris.asphanorisbeta.repository.IVehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VehicleService {
    
    @Autowired
    private IVehicleRepository vehicleRepo;
    
    private VehicleFactory vehicleFactory = new VehicleFactory() {
        @Override
        public ConVehicle createVehicle(VehicleRequestDTO newVehicle) {
            log.debug("Creando vehículo con placa: {}", newVehicle.getPlate());
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
        log.debug("Mapeando vehículo ID: {} a DTO", vehicle.getId());
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
        log.info("Creando vehículo con placa: {}, tipo: {}", vehicleDto.getPlate(), vehicleDto.getType());
        try {
            ConVehicle vehicle = vehicleFactory.createVehicle(vehicleDto);
            ConVehicle saved = vehicleRepo.addVehicle(vehicle);
            log.info("Vehículo creado exitosamente con ID: {} y placa: {}", saved.getId(), saved.getPlate());
            return convertToDTO(saved);
        } catch (Exception e) {
            log.error("Error al crear vehículo con placa: {}", vehicleDto.getPlate(), e);
            throw e;
        }
    }
    
    public VehicleResponseDTO modifyVehicle(Long vehicleId, VehicleRequestDTO vehicleDto) {
        log.info("Modificando vehículo ID: {}", vehicleId);
        ConVehicle existing = vehicleRepo.getVehicle(vehicleId);
        if (existing != null) {
            log.debug("Vehículo encontrado, actualizando campos");
            
            if (vehicleDto.getType() != null) {
                existing.setType(vehicleDto.getType());
                log.debug("Tipo actualizado a: {}", vehicleDto.getType());
            }
            if (vehicleDto.getPlate() != null) {
                existing.setPlate(vehicleDto.getPlate());
                log.debug("Placa actualizada a: {}", vehicleDto.getPlate());
            }
            if (vehicleDto.getPeopleCapacity() != null) {
                existing.setPeopleCapacity(vehicleDto.getPeopleCapacity());
                log.debug("Capacidad actualizada a: {}", vehicleDto.getPeopleCapacity());
            }
            if (vehicleDto.getCargoSize() != null) {
                existing.setCargoSize(vehicleDto.getCargoSize());
                log.debug("CargoSize actualizado a: {}", vehicleDto.getCargoSize());
            }
            if (vehicleDto.getCurrentKm() != null) {
                existing.setCurrentKm(vehicleDto.getCurrentKm());
                log.debug("Kilometraje actualizado a: {}", vehicleDto.getCurrentKm());
            }
            
            ConVehicle updated = vehicleRepo.modifyVehicle(existing);
            log.info("Vehículo ID: {} modificado exitosamente", vehicleId);
            return convertToDTO(updated);
        }
        log.error("Vehículo no encontrado con ID: {}", vehicleId);
        throw new RuntimeException("Vehicle not found with id: " + vehicleId);
    }
    
    public void deleteVehicle(Long vehicleId) {
        log.warn("Eliminando vehículo ID: {}", vehicleId);
        ConVehicle vehicle = vehicleRepo.getVehicle(vehicleId);
        if (vehicle != null) {
            vehicleRepo.deleteVehicle(vehicle);
            log.info("Vehículo ID: {} eliminado exitosamente", vehicleId);
        } else {
            log.error("Vehículo no encontrado con ID: {}", vehicleId);
            throw new RuntimeException("Vehicle not found with id: " + vehicleId);
        }
    }
    
    public VehicleResponseDTO getVehicle(Long vehicleId) {
        log.debug("Buscando vehículo con ID: {}", vehicleId);
        ConVehicle vehicle = vehicleRepo.getVehicle(vehicleId);
        if (vehicle != null) {
            log.debug("Vehículo encontrado: placa {}", vehicle.getPlate());
            return convertToDTO(vehicle);
        }
        log.warn("Vehículo no encontrado con ID: {}", vehicleId);
        throw new RuntimeException("Vehicle not found with id: " + vehicleId);
    }
    
    public List<VehicleResponseDTO> getAllVehicles() {
        log.info("Obteniendo todos los vehículos");
        List<VehicleResponseDTO> vehicles = vehicleRepo.getAllVehicles().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        log.info("Total de vehículos obtenidos: {}", vehicles.size());
        return vehicles;
    }
    
    public VehicleResponseDTO updateKm(Long vehicleId, Double newKm) {
        log.info("Actualizando kilometraje del vehículo ID: {} a {} km", vehicleId, newKm);
        ConVehicle vehicle = vehicleRepo.getVehicle(vehicleId);
        if (vehicle != null) {
            vehicle.updateKm(newKm);
            ConVehicle updated = vehicleRepo.modifyVehicle(vehicle);
            log.info("Kilometraje actualizado exitosamente para vehículo ID: {}", vehicleId);
            return convertToDTO(updated);
        }
        log.error("Vehículo no encontrado con ID: {}", vehicleId);
        throw new RuntimeException("Vehicle not found with id: " + vehicleId);
    }
}