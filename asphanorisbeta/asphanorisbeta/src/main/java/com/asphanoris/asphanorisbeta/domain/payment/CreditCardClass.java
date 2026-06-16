package com.asphanoris.asphanorisbeta.domain.payment;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CreditCardClass implements PaymentMethod {
    private String type = "CREDIT_CARD";
    private String number;
    private LocalDate expireDate;
    
    @Override
    public String getType() {
        return type;
    }
}