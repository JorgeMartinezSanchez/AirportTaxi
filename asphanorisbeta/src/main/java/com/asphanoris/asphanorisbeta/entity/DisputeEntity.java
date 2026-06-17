package com.asphanoris.asphanorisbeta.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "disputes")
@Data
@NoArgsConstructor
public class DisputeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "trip_order_id")
    private Long orderId;
    
    @Column(name = "passenger_id")
    private Long initiatorId;
    
    @Column(name = "title")
    private String title;
    
    @Column(length = 1000)
    private String description;
    
    @Column(length = 20)
    private String state = "IN_PROCESS";
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "trip_order_id", insertable = false, updatable = false)
    private TripOrderEntity order;

    @ManyToOne
    @JoinColumn(name = "passenger_id", insertable = false, updatable = false)
    private PassengerEntity initiator;
}