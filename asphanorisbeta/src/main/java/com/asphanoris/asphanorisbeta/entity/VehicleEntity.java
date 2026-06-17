package com.asphanoris.asphanorisbeta.entity;

import com.asphanoris.asphanorisbeta.enums.CargoSize;
import com.asphanoris.asphanorisbeta.enums.VehicleType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private VehicleType type;
    
    @Column(name = "plate", unique = true, length = 20)
    private String plate;
    
    @Column(name = "people_capacity")
    private Integer peopleCapacity;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "cargo_size", length = 10)
    private CargoSize cargoSize;
    
    @Column(name = "current_km")
    private Double currentKm = 0.0;
    
    // Un vehículo puede tener muchos conductores (históricamente)
    @OneToMany(mappedBy = "vehicle")
    private List<DriverEntity> drivers = new ArrayList<>();
}