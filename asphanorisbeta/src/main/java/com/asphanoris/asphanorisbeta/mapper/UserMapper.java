package com.asphanoris.asphanorisbeta.mapper;

import com.asphanoris.asphanorisbeta.domain.Admin;
import com.asphanoris.asphanorisbeta.domain.ConDriver;
import com.asphanoris.asphanorisbeta.domain.ConPassenger;
import com.asphanoris.asphanorisbeta.domain.User;
import com.asphanoris.asphanorisbeta.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    
    public UserEntity toEntity(User domain) {
        if (domain == null) return null;
        
        UserEntity entity = new UserEntity() {
        };
        entity.setId(domain.getId());
        entity.setFirstName(domain.getFirstName());
        entity.setSecondName(domain.getSecondName());
        entity.setLastName(domain.getLastName());
        entity.setEmail(domain.getEmail());
        entity.setPhoneNumber(domain.getPhoneNumber());
        entity.setPhotoUrl(domain.getPhotoUrl());
        entity.setPasswordHash(domain.getPasswordHash());
        entity.setRole(domain.getRole());
        return entity;
    }
    
    public User toDomain(UserEntity entity) {
        if (entity == null) return null;
        
        User domain;
        switch (entity.getRole()) {
            case ADMIN:
                domain = new Admin();
                break;
            case DRIVER:
                domain = new ConDriver();
                break;
            case PASSENGER:
                domain = new ConPassenger();
                break;
            default:
                throw new IllegalArgumentException("Unknown role: " + entity.getRole());
        }
        
        domain.setId(entity.getId());
        domain.setFirstName(entity.getFirstName());
        domain.setSecondName(entity.getSecondName());
        domain.setLastName(entity.getLastName());
        domain.setEmail(entity.getEmail());
        domain.setPhoneNumber(entity.getPhoneNumber());
        domain.setPhotoUrl(entity.getPhotoUrl());
        domain.setPasswordHash(entity.getPasswordHash());
        domain.setRole(entity.getRole());
        
        return domain;
    }
    
    public void updateEntity(UserEntity entity, User domain) {
        if (domain.getFirstName() != null) entity.setFirstName(domain.getFirstName());
        if (domain.getSecondName() != null) entity.setSecondName(domain.getSecondName());
        if (domain.getLastName() != null) entity.setLastName(domain.getLastName());
        if (domain.getEmail() != null) entity.setEmail(domain.getEmail());
        if (domain.getPhoneNumber() != null) entity.setPhoneNumber(domain.getPhoneNumber());
        if (domain.getPhotoUrl() != null) entity.setPhotoUrl(domain.getPhotoUrl());
        if (domain.getPasswordHash() != null) entity.setPasswordHash(domain.getPasswordHash());
        if (domain.getRole() != null) entity.setRole(domain.getRole());
    }
}