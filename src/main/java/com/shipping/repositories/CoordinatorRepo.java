package com.shipping.repositories;

import com.shipping.Entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shipping.Entities.Coordinator;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinatorRepo extends JpaRepository<Coordinator,Integer> {
    public Coordinator findByUserName(String userName);
    public Coordinator findByPassword(String password);
}
