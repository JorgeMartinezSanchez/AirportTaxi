package com.asphanoris.asphanorisbeta.domain;

import com.asphanoris.asphanorisbeta.domain.payment.PaymentMethod;
import com.asphanoris.asphanorisbeta.dto.OrderRequestDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ConPassenger extends User {
    private List<PaymentMethod> paymentMethods = new ArrayList<>();
    
    public RealTripOrder createOrder(OrderRequestDTO newOrder) {
        RealTripOrder order = new RealTripOrder();
        order.setPassengerId(this.getId());
        order.setOriginAddress(newOrder.getOriginAddress());
        order.setDestinationAddress(newOrder.getDestinationAddress());
        order.setTripStartDate(newOrder.getTripStartDate());
        return order;
    }
    
    public PaymentMethod getPaymentMethod(String type) {
        return paymentMethods.stream()
            .filter(pm -> pm.getType().equals(type))
            .findFirst()
            .orElse(null);
    }
}