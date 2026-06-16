package com.asphanoris.asphanorisbeta.domain.states;

import com.asphanoris.asphanorisbeta.domain.RealTripOrder;
import com.asphanoris.asphanorisbeta.enums.OrderStatus;

public class CompletedState extends OrderState {
    public CompletedState(RealTripOrder tripOrder) {
        super(tripOrder);
    }
    
    @Override
    public void confirm() {
        throw new IllegalStateException("Order already completed");
    }
    
    @Override
    public void start() {
        throw new IllegalStateException("Trip already completed");
    }
    
    @Override
    public void complete() {
        throw new IllegalStateException("Trip already completed");
    }
    
    @Override
    public void cancel() {
        throw new IllegalStateException("Cannot cancel completed order");
    }
    
    @Override
    public void dispute() {
        System.out.println("Dispute opened for completed order");
    }
    
    @Override
    public OrderState getNextState() {
        return this;
    }
    
    @Override
    public OrderStatus getStatus() {
        return OrderStatus.COMPLETED;
    }
}