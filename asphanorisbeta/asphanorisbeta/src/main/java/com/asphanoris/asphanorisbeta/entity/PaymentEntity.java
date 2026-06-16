package com.asphanoris.asphanorisbeta.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "trip_order_id")
    private Long tripOrderId;
    
    @Column(name = "payment_method_id")
    private Long paymentMethodId;
    
    private Double amount;
    
    @Column(length = 20)
    private String status = "PENDING";
    
    @Column(name = "transaction_id")
    private String transactionId;
    
    @Column(name = "payment_date")
    private LocalDateTime paymentDate = LocalDateTime.now();
    
    @Column(name = "release_date")
    private LocalDateTime releaseDate;
    
    // Relación Many-to-One con TripOrderEntity
    @OneToOne
    @JoinColumn(name = "trip_order_id", insertable = false, updatable = false)
    private TripOrderEntity tripOrder;
    
    // Relación Many-to-One con PaymentMethodEntity
    @ManyToOne
    @JoinColumn(name = "payment_method_id", insertable = false, updatable = false)
    private PaymentMethodEntity paymentMethod;
}