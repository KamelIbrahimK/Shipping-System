package com.shipping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shipping.Entities.Seller;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepo extends JpaRepository<Seller, Integer> {

     Seller findByUserName(String userName);

     Seller findSellerBySellerId(Integer sellerId);

     Seller findByPassword(String password);
}
