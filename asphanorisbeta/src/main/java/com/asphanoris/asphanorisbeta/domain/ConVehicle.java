package com.asphanoris.asphanorisbeta.domain;

import com.asphanoris.asphanorisbeta.enums.VehicleType;
import com.asphanoris.asphanorisbeta.enums.CargoSize;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class ConVehicle {
    private Long id;
    private VehicleType type;
    private String plate;
    private Integer peopleCapacity;
    private CargoSize cargoSize;
    private Double currentKm;
    private List<byte[]> photos = new ArrayList<>();
    
    public void updateKm(Double newKm) {
        this.currentKm = newKm;
    }
}