
package com.freightfox.dispatch.dto;

import java.math.BigDecimal;

import com.freightfox.dispatch.enums.Priority;

public class AssignedOrderResponse {

    private String orderId;
    private double latitude;
    private double longitude;
    private String address;
    private BigDecimal packageWeight;
    private Priority priority;

    public AssignedOrderResponse(
            String orderId,
            double latitude,
            double longitude,
            String address,
            BigDecimal packageWeight,
            Priority priority) {

        this.orderId = orderId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.packageWeight = packageWeight;
        this.priority = priority;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
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
