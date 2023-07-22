package com.shipping.repositories;

import com.shipping.Entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepo extends JpaRepository<Seller,Integer> {
}
