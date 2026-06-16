package com.asphanoris.asphanorisbeta.domain.states;

import com.asphanoris.asphanorisbeta.domain.ConDriver;
import com.asphanoris.asphanorisbeta.domain.RealTripOrder;
import com.asphanoris.asphanorisbeta.dto.OrderRequestDTO;

public class SuspendedState extends DriverState {
    public SuspendedState(ConDriver driver) {
        super(driver);
    }
    
    @Override
    public RealTripOrder recieveOrder(OrderRequestDTO order) {
        throw new IllegalStateException("Driver is suspended. Cannot receive orders.");
    }
    
    @Override
    public String getState() {
        return "SUSPENDED";
    }
}