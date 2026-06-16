package com.asphanoris.asphanorisbeta.domain.states;

import com.asphanoris.asphanorisbeta.domain.RealTripOrder;
import com.asphanoris.asphanorisbeta.enums.OrderStatus;

public class InProgressState extends OrderState {
    public InProgressState(RealTripOrder tripOrder) {
        super(tripOrder);
    }
    
    @Override
    public void confirm() {
        throw new IllegalStateException("Order already in progress");
    }
    
    @Override
    public void start() {
        throw new IllegalStateException("Trip already started");
    }
    
    @Override
    public void complete() {
        System.out.println("Trip completed");
    }
    
    @Override
    public void cancel() {
        System.out.println("Order cancelled");
    }
    
    @Override
    public void dispute() {
        System.out.println("Dispute opened");
    }
    
    @Override
    public OrderState getNextState() {
        return new CompletedState(tripOrder);
    }
    
    @Override
    public OrderStatus getStatus() {
        return OrderStatus.IN_PROGRESS;
    }
}