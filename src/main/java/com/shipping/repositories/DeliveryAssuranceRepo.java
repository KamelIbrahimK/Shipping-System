package com.shipping.repositories;

import com.shipping.Entities.DeliveryAssurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAssuranceRepo extends JpaRepository<DeliveryAssurance,Integer> {
}
