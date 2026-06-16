package com.asphanoris.asphanorisbeta.repository;

import com.asphanoris.asphanorisbeta.domain.ConVehicle;
import com.asphanoris.asphanorisbeta.entity.VehicleEntity;
import com.asphanoris.asphanorisbeta.mapper.VehicleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class PostgreSQLVehicleRepository implements IVehicleRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private VehicleMapper vehicleMapper;
    
    @Override
    public ConVehicle addVehicle(ConVehicle vehicle) {
        VehicleEntity entity = vehicleMapper.toEntity(vehicle);
        entityManager.persist(entity);
        entityManager.flush();
        return vehicleMapper.toDomain(entity);
    }
    
    @Override
    public ConVehicle modifyVehicle(ConVehicle vehicle) {
        VehicleEntity entity = entityManager.find(VehicleEntity.class, vehicle.getId());
        if (entity == null) {
            throw new RuntimeException("Vehicle not found with id: " + vehicle.getId());
        }
        vehicleMapper.updateEntity(entity, vehicle);
        VehicleEntity updated = entityManager.merge(entity);
        return vehicleMapper.toDomain(updated);
    }
    
    @Override
    public void deleteVehicle(ConVehicle vehicle) {
        VehicleEntity entity = entityManager.find(VehicleEntity.class, vehicle.getId());
        if (entity != null) {
            entityManager.remove(entity);
        }
    }
    
    @Override
    public ConVehicle getVehicle(Long vehicleId) {
        VehicleEntity entity = entityManager.find(VehicleEntity.class, vehicleId);
        return entity != null ? vehicleMapper.toDomain(entity) : null;
    }
    
    @Override
    public List<ConVehicle> getAllVehicles() {
        TypedQuery<VehicleEntity> query = entityManager.createQuery(
            "SELECT v FROM VehicleEntity v", VehicleEntity.class);
        List<VehicleEntity> entities = query.getResultList();
        return entities.stream()
            .map(vehicleMapper::toDomain)
            .collect(Collectors.toList());
    }
}