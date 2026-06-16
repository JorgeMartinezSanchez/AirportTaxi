package com.asphanoris.asphanorisbeta.mapper;

import com.asphanoris.asphanorisbeta.domain.RealDispute;
import com.asphanoris.asphanorisbeta.entity.DisputeEntity;
import org.springframework.stereotype.Component;

@Component
public class DisputeMapper {
    
    public DisputeEntity toEntity(RealDispute domain) {
        if (domain == null) return null;
        
        DisputeEntity entity = new DisputeEntity();
        entity.setId(domain.getId());
        entity.setOrderId(domain.getOrderId());
        entity.setInitiatorId(domain.getInitiatorId());
        entity.setTitle(domain.getTitle());
        entity.setDescription(domain.getDescription());
        entity.setState(domain.getState().getState());
        return entity;
    }
    
    public RealDispute toDomain(DisputeEntity entity) {
        if (entity == null) return null;
        
        RealDispute domain = new RealDispute();
        domain.setId(entity.getId());
        domain.setOrderId(entity.getOrderId());
        domain.setInitiatorId(entity.getInitiatorId());
        domain.setTitle(entity.getTitle());
        domain.setDescription(entity.getDescription());
        // El estado se mantiene como está (InProcessState por defecto en el constructor)
        return domain;
    }
    
    public void updateEntity(DisputeEntity entity, RealDispute domain) {
        if (domain.getTitle() != null) entity.setTitle(domain.getTitle());
        if (domain.getDescription() != null) entity.setDescription(domain.getDescription());
        if (domain.getState() != null) entity.setState(domain.getState().getState());
    }
}