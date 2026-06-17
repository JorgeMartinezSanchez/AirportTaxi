package com.asphanoris.asphanorisbeta.domain.payment;

import lombok.Data;

@Data
public class CashPaymentClass implements PaymentMethod {
    private String type = "CASH";
    
    @Override
    public String getType() {
        return type;
    }
}