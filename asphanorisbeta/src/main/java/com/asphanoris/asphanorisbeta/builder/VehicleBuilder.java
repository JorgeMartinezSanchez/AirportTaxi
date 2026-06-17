package com.asphanoris.asphanorisbeta.builder;

import com.asphanoris.asphanorisbeta.domain.ConVehicle;
import com.asphanoris.asphanorisbeta.enums.VehicleType;
import com.asphanoris.asphanorisbeta.enums.CargoSize;

public class VehicleBuilder {
    private ConVehicle vehicle;
    
    public VehicleBuilder() {
        reset();
    }
    
    public VehicleBuilder reset() {
        this.vehicle = new ConVehicle();
        return this;
    }
    
    public VehicleBuilder setType(VehicleType type) {
        this.vehicle.setType(type);
        return this;
    }
    
    public VehicleBuilder setPlate(String plate) {
        this.vehicle.setPlate(plate);
        return this;
    }
    
    public VehicleBuilder setPeopleCapacity(Integer capacity) {
        this.vehicle.setPeopleCapacity(capacity);
        return this;
    }
    
    public VehicleBuilder setCargoSize(CargoSize cargoSize) {
        this.vehicle.setCargoSize(cargoSize);
        return this;
    }
    
    public VehicleBuilder setCurrentKm(Double km) {
        this.vehicle.setCurrentKm(km);
        return this;
    }
    
    public ConVehicle build() {
        return this.vehicle;
    }
}