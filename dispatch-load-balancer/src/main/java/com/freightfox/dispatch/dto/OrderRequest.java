
package com.freightfox.dispatch.dto;

import java.util.List;

import com.freightfox.dispatch.entity.DeliveryOrder;

public class OrderRequest 
{

    private List<DeliveryOrder> orders;

    public OrderRequest() 
    {
    }

    public List<DeliveryOrder> getOrders() 
    {
        return orders;
    }

    public void setOrders(List<DeliveryOrder> orders)
    {
        this.orders = orders;
    }
}
