package com.shipping.repositories;

import com.shipping.Entities.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepo extends JpaRepository<CustomerOrder,Integer> {
}
