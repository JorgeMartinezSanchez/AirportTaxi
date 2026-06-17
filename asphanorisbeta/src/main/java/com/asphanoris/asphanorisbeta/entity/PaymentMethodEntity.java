package com.asphanoris.asphanorisbeta.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "payment_methods")
@Data
@NoArgsConstructor
public class PaymentMethodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "type", length = 20)
    private String type;
    
    @Column(name = "number", length = 50)
    private String number;
    
    // Un método de pago pertenece a un pasajero
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private PassengerEntity passenger;
    
    // Un método de pago puede tener muchos pagos
    @OneToMany(mappedBy = "paymentMethod")
    private List<PaymentEntity> payments = new ArrayList<>();
}