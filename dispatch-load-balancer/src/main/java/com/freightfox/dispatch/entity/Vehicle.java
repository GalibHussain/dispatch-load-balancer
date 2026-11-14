package com.freightfox.dispatch.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="VEHICLES")
public class Vehicle 
{
	@Id
	@Column(name="VEHICLE_ID",nullable=false,length=50)
	private String vehicleId;
	@Column(nullable=false,precision=10,scale=2)
	private BigDecimal capacity;
	@Column(name="CURRENT_LATITUDE",nullable=false)
	private Double currentLatitude;
	@Column(name="CURRENT_LONGITUDE",nullable=false)
	private Double currentLongitude;
	@Column(name="CURRENT_ADDRESS", nullable=false,length=500)
	private String currentAddress;
	
	
	public Vehicle()
	{
		
	}


	public String getVehicleId() 
	{
		return vehicleId;
	}


	public void setVehicleId(String vehicleId) 
	{
		this.vehicleId = vehicleId;
	}


	public BigDecimal getCapacity() 
	{
		return capacity;
	}


	public void setCapacity(BigDecimal capacity) 
	{
		this.capacity = capacity;
	}


	public Double getCurrentLatitude() 
	{
	    return currentLatitude;
	}


	public void setCurrentLatitude(Double currentLatitude) 
	{
	    this.currentLatitude = currentLatitude;
	}


	public Double getCurrentLongitude() 
	{
	    return currentLongitude;
	}


	public void setCurrentLongitude(Double currentLongitude) 
	{
	    this.currentLongitude = currentLongitude;
	}


	public String getCurrentAddress() {
		return currentAddress;
	}


	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	
	

}
