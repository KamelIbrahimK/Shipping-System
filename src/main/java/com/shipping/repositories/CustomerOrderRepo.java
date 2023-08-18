package com.shipping.repositories;

import com.shipping.Entities.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerOrderRepo extends JpaRepository<CustomerOrder,Integer> {

    CustomerOrder findByOrderId(Integer orderId);
    List<CustomerOrder> findAllOrdersBySellerId(Integer sellerId);

    int deleteOrderByOrderId(Integer orderId);

    List<CustomerOrder> findAllOrdersByDeliveryassuranceId(Integer daId);
}
