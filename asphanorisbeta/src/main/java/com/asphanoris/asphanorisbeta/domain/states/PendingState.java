package com.asphanoris.asphanorisbeta.domain.states;

import com.asphanoris.asphanorisbeta.domain.RealTripOrder;
import com.asphanoris.asphanorisbeta.enums.OrderStatus;

public class PendingState extends OrderState {
    public PendingState(RealTripOrder tripOrder) {
        super(tripOrder);
    }
    
    @Override
    public void confirm() {
        System.out.println("Order confirmed");
    }
    
    @Override
    public void start() {
        throw new IllegalStateException("Cannot start a pending order. Confirm first.");
    }
    
    @Override
    public void complete() {
        throw new IllegalStateException("Cannot complete a pending order.");
    }
    
    @Override
    public void cancel() {
        System.out.println("Order cancelled");
    }
    
    @Override
    public void dispute() {
        throw new IllegalStateException("Cannot dispute a pending order.");
    }
    
    @Override
    public OrderState getNextState() {
        return new ConfirmedState(tripOrder);
    }
    
    @Override
    public OrderStatus getStatus() {
        return OrderStatus.PENDING;
    }
}