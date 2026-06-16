package com.asphanoris.asphanorisbeta.entity;

import com.asphanoris.asphanorisbeta.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trip_orders")
@Data
@NoArgsConstructor
public class TripOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "passenger_id")
    private Long passengerId;
    
    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "origin_address")
    private String originAddress;

    @Column(name = "destination_address")
    private String destinationAddress;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private OrderStatus state = OrderStatus.PENDING;
    
    @Column(name = "trip_start_date")
    private LocalDateTime tripStartDate;
    
    @Column(name = "trip_end_date")
    private LocalDateTime tripEndDate;
    
    @Column(name = "total")
    private Double total;
    
    @Column(name = "order_creation_date")
    private LocalDateTime orderCreationDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "passenger_id", insertable = false, updatable = false)
    private PassengerEntity passenger;
    
    @ManyToOne
    @JoinColumn(name = "driver_id", insertable = false, updatable = false)
    private DriverEntity driver;

    @OneToOne(mappedBy = "tripOrder")
    private PaymentEntity payment;

    @OneToMany(mappedBy = "order")
    private List<DisputeEntity> disputes = new ArrayList<>();
}