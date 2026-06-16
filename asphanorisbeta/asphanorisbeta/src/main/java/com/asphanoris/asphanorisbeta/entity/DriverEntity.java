package com.asphanoris.asphanorisbeta.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drivers")
@PrimaryKeyJoinColumn(name = "user_id")
@Data
@EqualsAndHashCode(callSuper = true)
public class DriverEntity extends UserEntity {
    
    @Column(length = 20)
    private String state = "OFFLINE";
    
    @Column(precision = 3, scale = 2)
    private BigDecimal rate = BigDecimal.valueOf(5.0);;
    
    @Column(name = "vehicle_id")
    private Long vehicleId;
    
    // Relación Many-to-One con VehicleEntity
    @ManyToOne
    @JoinColumn(name = "vehicle_id", insertable = false, updatable = false)
    private VehicleEntity vehicle;
    
    // Un conductor tiene muchas órdenes
    @OneToMany(mappedBy = "driver")
    private List<TripOrderEntity> orders = new ArrayList<>();
}