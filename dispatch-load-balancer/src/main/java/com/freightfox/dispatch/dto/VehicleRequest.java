package com.freightfox.dispatch.dto;

import java.util.List;
import com.freightfox.dispatch.entity.Vehicle;

public class VehicleRequest 
{

    private List<Vehicle> vehicles;

    public VehicleRequest() 
    {
    }

    public List<Vehicle> getVehicles() 
    {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) 
    {
        this.vehicles = vehicles;
    }
}