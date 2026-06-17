package com.asphanoris.asphanorisbeta.domain.states;

import com.asphanoris.asphanorisbeta.domain.ConDriver;
import com.asphanoris.asphanorisbeta.domain.RealTripOrder;
import com.asphanoris.asphanorisbeta.dto.OrderRequestDTO;

public class OfflineState extends DriverState {
    public OfflineState(ConDriver driver) {
        super(driver);
    }
    
    @Override
    public RealTripOrder recieveOrder(OrderRequestDTO order) {
        throw new IllegalStateException("Driver is offline. Cannot receive orders.");
    }
    
    @Override
    public String getState() {
        return "OFFLINE";
    }
}