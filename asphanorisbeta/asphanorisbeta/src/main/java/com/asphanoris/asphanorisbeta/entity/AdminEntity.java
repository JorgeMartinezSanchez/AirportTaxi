package com.asphanoris.asphanorisbeta.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "admins")
@PrimaryKeyJoinColumn(name = "user_id")
@Data
@EqualsAndHashCode(callSuper = true)
public class AdminEntity extends UserEntity {
    
    @Column(name = "admin_role", length = 50)
    private String adminRole;
}