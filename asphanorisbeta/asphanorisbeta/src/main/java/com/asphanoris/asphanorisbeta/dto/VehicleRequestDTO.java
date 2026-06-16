package com.asphanoris.asphanorisbeta.dto;

import com.asphanoris.asphanorisbeta.enums.VehicleType;
import com.asphanoris.asphanorisbeta.enums.CargoSize;
import lombok.Data;

@Data
public class VehicleRequestDTO {
    private VehicleType type;
    private String plate;
    private Integer peopleCapacity;
    private CargoSize cargoSize;
    private Double currentKm;
}