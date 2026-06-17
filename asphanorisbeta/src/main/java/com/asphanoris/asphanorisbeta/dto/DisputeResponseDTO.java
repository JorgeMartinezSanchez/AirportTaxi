package com.asphanoris.asphanorisbeta.dto;

import lombok.Data;

@Data
public class DisputeResponseDTO {
    private Long id;
    private Long orderId;
    private Long initiatorId;
    private String title;
    private String description;
    private String state;
}