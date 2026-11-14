
package com.freightfox.dispatch.dto;

import java.util.List;

public class DispatchPlanResponse {

    private List<VehicleDispatchResponse> dispatchPlan;
    private List<String> unassignedOrders;

    public DispatchPlanResponse() {
    }

    public DispatchPlanResponse(
            List<VehicleDispatchResponse> dispatchPlan,
            List<String> unassignedOrders) {
        this.dispatchPlan = dispatchPlan;
        this.unassignedOrders = unassignedOrders;
    }

    public List<VehicleDispatchResponse> getDispatchPlan() {
        return dispatchPlan;
    }

    public void setDispatchPlan(
            List<VehicleDispatchResponse> dispatchPlan) {
        this.dispatchPlan = dispatchPlan;
    }

    public List<String> getUnassignedOrders() {
        return unassignedOrders;
    }

    public void setUnassignedOrders(
            List<String> unassignedOrders) {
        this.unassignedOrders = unassignedOrders;
    }
}
