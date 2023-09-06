package com.shipping.repositories;

import com.shipping.Entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shipping.Entities.DeliveryAssurance;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryAssuranceRepo extends JpaRepository<DeliveryAssurance,Integer> {
    public DeliveryAssurance findByUserName(String userName);
    public DeliveryAssurance findByPassword(String password);
}
