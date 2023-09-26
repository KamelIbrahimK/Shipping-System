package com.shipping.repositories;

import com.shipping.Entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shipping.Entities.Coordinator;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinatorRepo extends JpaRepository<Coordinator,Integer> {
     Coordinator findByUserName(String userName);
     Coordinator findByPassword(String password);
}
