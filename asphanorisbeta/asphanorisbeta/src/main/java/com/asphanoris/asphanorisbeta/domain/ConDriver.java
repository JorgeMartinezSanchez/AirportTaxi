package com.asphanoris.asphanorisbeta.domain;

import com.asphanoris.asphanorisbeta.domain.states.DriverState;
import com.asphanoris.asphanorisbeta.domain.states.OfflineState;
import com.asphanoris.asphanorisbeta.dto.OrderRequestDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ConDriver extends User {
    private DriverState state;
    private Double rate;
    private ConVehicle vehicle;
    private Boolean bankPaymentEnabled = false;
    private Boolean bitcoinPaymentEnabled = false;
    
    public ConDriver() {
        this.state = new OfflineState(this);
        this.rate = 5.0;
    }
    
    public void changeState(DriverState newState) {
        this.state = newState;
    }
    
    public void setBankPayment(Boolean enabled) {
        this.bankPaymentEnabled = enabled;
    }
    
    public void setBitcoinPayment(Boolean enabled) {
        this.bitcoinPaymentEnabled = enabled;
    }
    
    public RealTripOrder recieveOrder(OrderRequestDTO orderDto) {
        return this.state.recieveOrder(orderDto);
    }
    
    public User modifyUser(User user) {
        throw new Error("Driver cant mo");
    }
}