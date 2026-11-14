package com.freightfox.dispatch.entity;

import java.math.BigDecimal;

import com.freightfox.dispatch.enums.Priority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="DELIVERY_ORDERS")

public class DeliveryOrder 
{
	@Id
	@Column(name="ORDER_ID",nullable=false, length=50)
	private String orderId;
	@Column(nullable=false)
	private Double latitude;
	@Column(nullable=false)
	private Double longitude;
	@Column(nullable=false,length=500)
	private String address;
	@Column(name = "PACKAGE_WEIGHT", nullable = false, precision = 10, scale = 2)
	private BigDecimal packageWeight;
	@Enumerated(EnumType.STRING)
	@Column(nullable=false,length=10)
	private Priority priority;
	
	public DeliveryOrder()
	{
		
	}
	
	
	public String getOrderId() {
	    return orderId;
	}

	public void setOrderId(String orderId) {
	    this.orderId = orderId;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getPackageWeight() {
		return packageWeight;
	}

	public void setPackageWeight(BigDecimal packageWeight) {
		this.packageWeight = packageWeight;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
	
	

}
