package com.asphanoris.asphanorisbeta.entity;

import com.asphanoris.asphanorisbeta.enums.RoleType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
public abstract class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "first_name", length = 100)
    private String firstName;
    
    @Column(name = "second_name", length = 100)
    private String secondName;
    
    @Column(name = "last_name", length = 100)
    private String lastName;
    
    @Column(unique = true, length = 150)
    private String email;
    
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;
    
    @Column(name = "photo_url")
    private String photoUrl;
    
    @Column(name = "password_hash", length = 255)
    private String passwordHash;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleType role;
}