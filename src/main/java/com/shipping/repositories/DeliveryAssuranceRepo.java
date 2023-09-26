package com.shipping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shipping.Entities.DeliveryAssurance;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryAssuranceRepo extends JpaRepository<DeliveryAssurance,Integer> {
    DeliveryAssurance findByUserName(String userName);
}
