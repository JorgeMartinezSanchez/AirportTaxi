package com.asphanoris.asphanorisbeta.repository;

import com.asphanoris.asphanorisbeta.domain.RealTripOrder;
import com.asphanoris.asphanorisbeta.entity.TripOrderEntity;
import com.asphanoris.asphanorisbeta.mapper.OrderMapper;
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
public class PostgreSQLOrderRepository implements IOrderRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private OrderMapper orderMapper;
    
    @Override
    public RealTripOrder addOrder(RealTripOrder order) {
        TripOrderEntity entity = orderMapper.toEntity(order);
        entityManager.persist(entity);
        entityManager.flush();
        return orderMapper.toDomain(entity);
    }
    
    @Override
    public RealTripOrder modifyOrder(RealTripOrder order) {
        TripOrderEntity entity = entityManager.find(TripOrderEntity.class, order.getId());
        if (entity == null) {
            throw new RuntimeException("Order not found with id: " + order.getId());
        }
        orderMapper.updateEntity(entity, order);
        TripOrderEntity updated = entityManager.merge(entity);
        return orderMapper.toDomain(updated);
    }
    
    @Override
    public void deleteOrder(RealTripOrder order) {
        TripOrderEntity entity = entityManager.find(TripOrderEntity.class, order.getId());
        if (entity != null) {
            entityManager.remove(entity);
        }
    }
    
    @Override
    public RealTripOrder getOrder(Long orderId) {
        TripOrderEntity entity = entityManager.find(TripOrderEntity.class, orderId);
        return entity != null ? orderMapper.toDomain(entity) : null;
    }
    
    @Override
    public List<RealTripOrder> getAllOrders() {
        TypedQuery<TripOrderEntity> query = entityManager.createQuery(
            "SELECT o FROM TripOrderEntity o", TripOrderEntity.class);
        List<TripOrderEntity> entities = query.getResultList();
        return entities.stream()
            .map(orderMapper::toDomain)
            .collect(Collectors.toList());
    }
}