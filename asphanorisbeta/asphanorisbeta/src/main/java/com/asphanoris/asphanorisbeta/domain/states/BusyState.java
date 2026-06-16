package com.asphanoris.asphanorisbeta.domain.states;

import com.asphanoris.asphanorisbeta.domain.ConDriver;
import com.asphanoris.asphanorisbeta.domain.RealTripOrder;
import com.asphanoris.asphanorisbeta.dto.OrderRequestDTO;

public class BusyState extends DriverState {
    public BusyState(ConDriver driver) {
        super(driver);
    }
    
    @Override
    public RealTripOrder recieveOrder(OrderRequestDTO order) {
        throw new IllegalStateException("Driver is busy. Cannot receive new orders.");
    }
    
    @Override
    public String getState() {
        return "BUSY";
    }
}