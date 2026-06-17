package com.asphanoris.asphanorisbeta.repository;

import com.asphanoris.asphanorisbeta.domain.ConVehicle;
import java.util.List;

public interface IVehicleRepository {
    ConVehicle addVehicle(ConVehicle vehicle);
    ConVehicle modifyVehicle(ConVehicle vehicle);
    void deleteVehicle(ConVehicle vehicle);
    ConVehicle getVehicle(Long vehicleId);
    List<ConVehicle> getAllVehicles();
}