package com.asphanoris.asphanorisbeta.domain.states;

import com.asphanoris.asphanorisbeta.domain.RealTripOrder;
import com.asphanoris.asphanorisbeta.enums.OrderStatus;

public abstract class OrderState {
    protected RealTripOrder tripOrder;
    
    public OrderState(RealTripOrder tripOrder) {
        this.tripOrder = tripOrder;
    }
    
    public abstract void confirm();
    public abstract void start();
    public abstract void complete();
    public abstract void cancel();
    public abstract void dispute();
    public abstract OrderState getNextState();
    public abstract OrderStatus getStatus();
}