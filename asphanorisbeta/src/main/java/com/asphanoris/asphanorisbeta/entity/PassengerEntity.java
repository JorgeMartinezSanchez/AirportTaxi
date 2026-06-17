package com.asphanoris.asphanorisbeta.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "passengers")
@PrimaryKeyJoinColumn(name = "user_id")
@Data
@EqualsAndHashCode(callSuper = true)
public class PassengerEntity extends UserEntity {
    
    @Column(name = "payment_method_id")
    private Long paymentMethodId;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", insertable = false, updatable = false)
    private PaymentMethodEntity paymentMethod;
    
    @OneToMany(mappedBy = "passenger")
    private List<TripOrderEntity> orders = new ArrayList<>();

    @OneToMany(mappedBy = "initiator")
    private List<DisputeEntity> disputes = new ArrayList<>();
}