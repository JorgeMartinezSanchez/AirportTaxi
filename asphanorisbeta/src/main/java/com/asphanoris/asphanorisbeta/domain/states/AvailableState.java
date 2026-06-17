package com.asphanoris.asphanorisbeta.domain.states;

import com.asphanoris.asphanorisbeta.domain.ConDriver;
import com.asphanoris.asphanorisbeta.domain.RealTripOrder;
import com.asphanoris.asphanorisbeta.dto.OrderRequestDTO;

public class AvailableState extends DriverState {
    public AvailableState(ConDriver driver) {
        super(driver);
    }
    
    @Override
    public RealTripOrder recieveOrder(OrderRequestDTO order) {
        RealTripOrder tripOrder = new RealTripOrder();
        tripOrder.setDriverId(driver.getId());
        tripOrder.setOriginAddress(order.getOriginAddress());
        tripOrder.setDestinationAddress(order.getDestinationAddress());
        driver.changeState(new BusyState(driver));
        return tripOrder;
    }
    
    @Override
    public String getState() {
        return "AVAILABLE";
    }
}