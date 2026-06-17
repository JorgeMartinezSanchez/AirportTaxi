package com.asphanoris.asphanorisbeta.repository;

import com.asphanoris.asphanorisbeta.domain.User;
import com.asphanoris.asphanorisbeta.entity.UserEntity;
import com.asphanoris.asphanorisbeta.mapper.UserMapper;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class PostgreSQLUserRepository implements IUserRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    private final UserMapper userMapper;
    
    // ✅ Inyección por constructor
    public PostgreSQLUserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    
    @Override
    public User addUser(User user) {
        UserEntity entity = userMapper.toEntity(user);
        entityManager.persist(entity);
        entityManager.flush();
        return userMapper.toDomain(entity);
    }
    
    @Override
    public User modifyUser(User user) {
        UserEntity entity = entityManager.find(UserEntity.class, user.getId());
        if (entity == null) {
            throw new RuntimeException("User not found with id: " + user.getId());
        }
        userMapper.updateEntity(entity, user);
        UserEntity updated = entityManager.merge(entity);
        return userMapper.toDomain(updated);
    }
    
    @Override
    public void deleteUser(User user) {
        UserEntity entity = entityManager.find(UserEntity.class, user.getId());
        if (entity != null) {
            entityManager.remove(entity);
        }
    }
    
    @Override
    public User getUser(Long userId) {
        UserEntity entity = entityManager.find(UserEntity.class, userId);
        return entity != null ? userMapper.toDomain(entity) : null;
    }
    
    @Override
    public List<User> getAllUsers() {
        TypedQuery<UserEntity> query = entityManager.createQuery(
            "SELECT u FROM UserEntity u", UserEntity.class);
        List<UserEntity> entities = query.getResultList();
        return entities.stream()
            .map(userMapper::toDomain)
            .collect(Collectors.toList());
    }
}