package com.shipping.Services;

import com.shipping.Entities.CustomerOrder;
import com.shipping.Entities.Seller;
import com.shipping.repositories.CustomerOrderRepo;
import com.shipping.repositories.SellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FinancialService {
    @Autowired
    private CustomerOrderRepo customerOrderRepo;
    @Autowired
    private SellerRepo sellerRepo;




    public double calculateAllOrdersPriceBySellerId(Integer sellerId){
        List<CustomerOrder> sellerOrders=customerOrderRepo.findAllOrdersBySellerId(sellerId);
        Optional<Seller>seller= sellerRepo.findById(sellerId);
        double ordersPrice=0.0;
        if (seller.isPresent()){
            for (int i = 0; i <= sellerOrders.size(); i++) {
               ordersPrice+=sellerOrders.get(i).getOrderPrice();
            }
            return ordersPrice;
        }
        return ordersPrice;
    }
}
