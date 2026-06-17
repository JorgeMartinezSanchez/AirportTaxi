package com.asphanoris.asphanorisbeta.domain;

import com.asphanoris.asphanorisbeta.domain.payment.PaymentMethod;
import com.asphanoris.asphanorisbeta.domain.states.OrderState;
import com.asphanoris.asphanorisbeta.domain.states.PendingState;
import com.asphanoris.asphanorisbeta.enums.OrderStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RealTripOrder {
    private Long id;
    private Long passengerId;
    private Long driverId;
    private String originAddress;
    private String destinationAddress;
    private LocalDateTime tripStartDate;
    private LocalDateTime tripEndDate;
    private PaymentMethod payment;
    private OrderState state;
    private LocalDateTime orderCreationDate;
    private Double total;
    
    public RealTripOrder() {
        this.state = new PendingState(this);
        this.orderCreationDate = LocalDateTime.now();
    }
    
    public void confirm() {
        this.state.confirm();
        this.state = this.state.getNextState();
    }
    
    public void start() {
        this.state.start();
        this.state = this.state.getNextState();
    }
    
    public void complete() {
        this.state.complete();
        this.state = this.state.getNextState();
    }
    
    public void cancel() {
        this.state.cancel();
        this.state = this.state.getNextState();
    }
    
    public void dispute() {
        this.state.dispute();
    }
    
    public void setPrice(Double price) {
        this.total = price;
    }
    
    public OrderStatus getStatus() {
        return this.state.getStatus();
    }
}