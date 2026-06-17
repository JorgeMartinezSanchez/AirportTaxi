package com.asphanoris.asphanorisbeta.domain.states;

import com.asphanoris.asphanorisbeta.domain.ConDriver;
import com.asphanoris.asphanorisbeta.domain.RealTripOrder;
import com.asphanoris.asphanorisbeta.dto.OrderRequestDTO;

public abstract class DriverState {
    protected ConDriver driver;
    
    public DriverState(ConDriver driver) {
        this.driver = driver;
    }
    
    public abstract RealTripOrder recieveOrder(OrderRequestDTO order);
    public abstract String getState();
}