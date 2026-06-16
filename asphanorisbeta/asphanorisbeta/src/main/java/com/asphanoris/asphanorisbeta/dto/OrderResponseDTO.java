package com.asphanoris.asphanorisbeta.dto;

import com.asphanoris.asphanorisbeta.enums.OrderStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderResponseDTO {
    private Long id;
    private Long passengerId;
    private Long driverId;
    private OrderStatus state;
    private LocalDateTime tripStartDate;
    private LocalDateTime tripEndDate;
    private Double total;
    private LocalDateTime orderCreationDate;
}