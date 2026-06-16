package com.asphanoris.asphanorisbeta.dto;

import lombok.Data;

@Data
public class DisputeRequestDTO {
    private Long orderId;
    private Long initiatorId;
    private String title;
    private String description;
}