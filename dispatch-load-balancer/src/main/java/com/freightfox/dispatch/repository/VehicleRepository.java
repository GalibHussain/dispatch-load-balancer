
package com.freightfox.dispatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freightfox.dispatch.entity.Vehicle;

public interface VehicleRepository
        extends JpaRepository<Vehicle, String> 
{

}
