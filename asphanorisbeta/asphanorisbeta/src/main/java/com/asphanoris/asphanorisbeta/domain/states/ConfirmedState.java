package com.asphanoris.asphanorisbeta.domain.states;

import com.asphanoris.asphanorisbeta.domain.RealTripOrder;
import com.asphanoris.asphanorisbeta.enums.OrderStatus;

public class ConfirmedState extends OrderState {
    public ConfirmedState(RealTripOrder tripOrder) {
        super(tripOrder);
    }
    
    @Override
    public void confirm() {
        throw new IllegalStateException("Order already confirmed");
    }
    
    @Override
    public void start() {
        System.out.println("Trip started");
    }
    
    @Override
    public void complete() {
        throw new IllegalStateException("Cannot complete before starting");
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
        return new InProgressState(tripOrder);
    }
    
    @Override
    public OrderStatus getStatus() {
        return OrderStatus.CONFIRMED;
    }
}