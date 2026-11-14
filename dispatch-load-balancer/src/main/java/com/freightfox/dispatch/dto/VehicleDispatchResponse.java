package com.freightfox.dispatch.dto;

import java.math.BigDecimal;

import java.util.List;

public class VehicleDispatchResponse 
{
	private String vehicleId;
	private BigDecimal totalLoad;
	private String totalDistance;
	private List<AssignedOrderResponse> assignedOrders;
	
	public VehicleDispatchResponse()
	{
		
	}
	public VehicleDispatchResponse
	(String vehicleId,
		BigDecimal totalLoad,
		String totalDistance,
		List<AssignedOrderResponse> assignedOrders)
	{
		 this.vehicleId = vehicleId;
	        this.totalLoad = totalLoad;
	        this.totalDistance = totalDistance;
	        this.assignedOrders = assignedOrders;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public BigDecimal getTotalLoad() {
		return totalLoad;
	}
	public void setTotalLoad(BigDecimal totalLoad) {
		this.totalLoad = totalLoad;
	}
	public String getTotalDistance() {
		return totalDistance;
	}
	public void setTotalDistance(String totalDistance) {
		this.totalDistance = totalDistance;
	}
	public List<AssignedOrderResponse> getAssignedOrders() {
		return assignedOrders;
	}
	public void setAssignedOrders(List<AssignedOrderResponse> assignedOrders) {
		this.assignedOrders = assignedOrders;
	}

	
	
}
