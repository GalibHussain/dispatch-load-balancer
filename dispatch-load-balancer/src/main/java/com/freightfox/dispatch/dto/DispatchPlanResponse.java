
package com.freightfox.dispatch.dto;

import java.util.List;

public class DispatchPlanResponse 

{

    private List<VehicleDispatchResponse> dispatchPlan;

    public DispatchPlanResponse() 
    {
    }

    public DispatchPlanResponse(
            List<VehicleDispatchResponse> dispatchPlan) 
    {
        this.dispatchPlan = dispatchPlan;
    }

    public List<VehicleDispatchResponse> getDispatchPlan() 
    {
        return dispatchPlan;
    }

    public void setDispatchPlan(
            List<VehicleDispatchResponse> dispatchPlan) 
    {
        this.dispatchPlan = dispatchPlan;
    }
}
