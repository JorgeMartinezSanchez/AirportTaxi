package com.asphanoris.asphanorisbeta.repository;

import com.asphanoris.asphanorisbeta.domain.RealDispute;
import com.asphanoris.asphanorisbeta.entity.DisputeEntity;
import com.asphanoris.asphanorisbeta.mapper.DisputeMapper;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class PostgreSQLDisputeRepository implements IDisputeRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    private final DisputeMapper disputeMapper;
    
    // ✅ Inyección por constructor
    public PostgreSQLDisputeRepository(DisputeMapper disputeMapper) {
        this.disputeMapper = disputeMapper;
    }
    
    @Override
    public RealDispute addDispute(RealDispute dispute) {
        DisputeEntity entity = disputeMapper.toEntity(dispute);
        entityManager.persist(entity);
        entityManager.flush();
        return disputeMapper.toDomain(entity);
    }
    
    @Override
    public RealDispute modifyDispute(RealDispute dispute) {
        DisputeEntity entity = entityManager.find(DisputeEntity.class, dispute.getId());
        if (entity == null) {
            throw new RuntimeException("Dispute not found with id: " + dispute.getId());
        }
        disputeMapper.updateEntity(entity, dispute);
        DisputeEntity updated = entityManager.merge(entity);
        return disputeMapper.toDomain(updated);
    }
    
    @Override
    public void deleteDispute(RealDispute dispute) {
        DisputeEntity entity = entityManager.find(DisputeEntity.class, dispute.getId());
        if (entity != null) {
            entityManager.remove(entity);
        }
    }
    
    @Override
    public RealDispute getDispute(Long disputeId) {
        DisputeEntity entity = entityManager.find(DisputeEntity.class, disputeId);
        return entity != null ? disputeMapper.toDomain(entity) : null;
    }
    
    @Override
    public List<RealDispute> getAllDisputes() {
        TypedQuery<DisputeEntity> query = entityManager.createQuery(
            "SELECT d FROM DisputeEntity d", DisputeEntity.class);
        List<DisputeEntity> entities = query.getResultList();
        return entities.stream()
            .map(disputeMapper::toDomain)
            .collect(Collectors.toList());
    }
}