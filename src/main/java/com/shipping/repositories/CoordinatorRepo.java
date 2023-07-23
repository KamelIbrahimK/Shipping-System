package com.shipping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shipping.Entities.Coordinator;

public interface CoordinatorRepo extends JpaRepository<Coordinator,Integer> {
    public Coordinator findByUserName(String userName);

}
