package com.shipping.repositories;

import com.shipping.Entities.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerOrderRepo extends JpaRepository<CustomerOrder , Integer>{

    List<CustomerOrder> findAllOrdersBySellerId(Integer sellerId);

    List<CustomerOrder> findAllOrdersByDeliveryassuranceId(Integer daId);

    List<CustomerOrder> getOrdersByStatus(String orderStatus);

}
