
package com.freightfox.dispatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freightfox.dispatch.entity.DeliveryOrder;

public interface DeliveryOrderRepository
        extends JpaRepository<DeliveryOrder, String> 
{

}
