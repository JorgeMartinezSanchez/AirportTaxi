package com.asphanoris.asphanorisbeta.mapper;

import com.asphanoris.asphanorisbeta.domain.RealTripOrder;
import com.asphanoris.asphanorisbeta.entity.TripOrderEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    
    public TripOrderEntity toEntity(RealTripOrder domain) {
        if (domain == null) return null;
        
        TripOrderEntity entity = new TripOrderEntity();
        entity.setId(domain.getId());
        entity.setPassengerId(domain.getPassengerId());
        entity.setDriverId(domain.getDriverId());
        entity.setOriginAddress(domain.getOriginAddress());
        entity.setDestinationAddress(domain.getDestinationAddress());
        entity.setState(domain.getStatus());
        entity.setTripStartDate(domain.getTripStartDate());
        entity.setTripEndDate(domain.getTripEndDate());
        entity.setTotal(domain.getTotal());
        entity.setOrderCreationDate(domain.getOrderCreationDate());
        return entity;
    }
    
    public RealTripOrder toDomain(TripOrderEntity entity) {
        if (entity == null) return null;
        
        RealTripOrder domain = new RealTripOrder();
        domain.setId(entity.getId());
        domain.setPassengerId(entity.getPassengerId());
        domain.setDriverId(entity.getDriverId());
        domain.setOriginAddress(entity.getOriginAddress());
        domain.setDestinationAddress(entity.getDestinationAddress());
        domain.setTripStartDate(entity.getTripStartDate());
        domain.setTripEndDate(entity.getTripEndDate());
        domain.setTotal(entity.getTotal());
        domain.setOrderCreationDate(entity.getOrderCreationDate());
        return domain;
    }
    
    public void updateEntity(TripOrderEntity entity, RealTripOrder domain) {
        if (domain.getPassengerId() != null) entity.setPassengerId(domain.getPassengerId());
        if (domain.getDriverId() != null) entity.setDriverId(domain.getDriverId());
        if (domain.getOriginAddress() != null) entity.setOriginAddress(domain.getOriginAddress());
        if (domain.getDestinationAddress() != null) entity.setDestinationAddress(domain.getDestinationAddress());
        if (domain.getStatus() != null) entity.setState(domain.getStatus());
        if (domain.getTripStartDate() != null) entity.setTripStartDate(domain.getTripStartDate());
        if (domain.getTripEndDate() != null) entity.setTripEndDate(domain.getTripEndDate());
        if (domain.getTotal() != null) entity.setTotal(domain.getTotal());
    }
}