package com.asphanoris.asphanorisbeta.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderRequestDTO {
    private Long passengerId;
    private Long driverId;
    private String originAddress;
    private String destinationAddress;
    private LocalDateTime tripStartDate;
    private LocalDateTime tripEndDate;
    private Double total;
}