package com.shipping.repositories;

import com.shipping.Entities.Coordinator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinatorRepo extends JpaRepository<Coordinator,Integer> {
}
