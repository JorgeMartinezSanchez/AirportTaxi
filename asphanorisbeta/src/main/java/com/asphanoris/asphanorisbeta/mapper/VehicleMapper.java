package com.asphanoris.asphanorisbeta.mapper;

import com.asphanoris.asphanorisbeta.domain.ConVehicle;
import com.asphanoris.asphanorisbeta.entity.VehicleEntity;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {
    
    public VehicleEntity toEntity(ConVehicle domain) {
        if (domain == null) return null;
        
        VehicleEntity entity = new VehicleEntity();
        entity.setId(domain.getId());
        entity.setType(domain.getType());
        entity.setPlate(domain.getPlate());
        entity.setPeopleCapacity(domain.getPeopleCapacity());
        entity.setCargoSize(domain.getCargoSize());
        entity.setCurrentKm(domain.getCurrentKm());
        return entity;
    }
    
    public ConVehicle toDomain(VehicleEntity entity) {
        if (entity == null) return null;
        
        ConVehicle domain = new ConVehicle();
        domain.setId(entity.getId());
        domain.setType(entity.getType());
        domain.setPlate(entity.getPlate());
        domain.setPeopleCapacity(entity.getPeopleCapacity());
        domain.setCargoSize(entity.getCargoSize());
        domain.setCurrentKm(entity.getCurrentKm());
        return domain;
    }
    
    public void updateEntity(VehicleEntity entity, ConVehicle domain) {
        if (domain.getType() != null) entity.setType(domain.getType());
        if (domain.getPlate() != null) entity.setPlate(domain.getPlate());
        if (domain.getPeopleCapacity() != null) entity.setPeopleCapacity(domain.getPeopleCapacity());
        if (domain.getCargoSize() != null) entity.setCargoSize(domain.getCargoSize());
        if (domain.getCurrentKm() != null) entity.setCurrentKm(domain.getCurrentKm());
    }
}