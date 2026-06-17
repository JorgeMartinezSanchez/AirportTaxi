package com.asphanoris.asphanorisbeta.factory;

import com.asphanoris.asphanorisbeta.domain.ConVehicle;
import com.asphanoris.asphanorisbeta.dto.VehicleRequestDTO;

public interface VehicleFactory {
    ConVehicle createVehicle(VehicleRequestDTO newVehicle);
}