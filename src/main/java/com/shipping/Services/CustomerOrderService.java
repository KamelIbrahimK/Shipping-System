package com.shipping.Services;

import com.shipping.Dtos.Order.*;
import com.shipping.Entities.Customer;
import com.shipping.Entities.CustomerOrder;
import com.shipping.Entities.Seller;
import com.shipping.repositories.CustomerOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerOrderService {
    @Autowired
    private CustomerOrderRepo customerOrderRepo;


    public List<CustomerOrder> saveOrder(List<CustomerOrderPostDto> orders) {
        // list of db models to hit the db once
        List<CustomerOrder> customerOrders = new ArrayList<>();

        CustomerOrder customerOrder = new CustomerOrder();
        for (int i = 0; i < orders.size(); i++) {
            // map request dto to  db model
            customerOrder.setOrderPrice(orders.get(i).getOrderPrice());
            customerOrder.setNumberOfItems(orders.get(i).getNumberOfItems());
            customerOrder.setSeller(new Seller(orders.get(i).getSellerId()));
            customerOrder.setCustomer(new Customer(orders.get(i).getCustomerId()));
            customerOrders.add(customerOrder);
        }

        return customerOrderRepo.saveAll(customerOrders);
    }

    public OrderResponse updateOrder(CustomerOrderUpdateDto newCustomerOrderDto) {
        Optional<CustomerOrder> optionalOldOrder = customerOrderRepo.findById(newCustomerOrderDto.getOrderId());
        OrderResponse response = new OrderResponse();

        if (optionalOldOrder.isPresent()) {
            CustomerOrder oldOrder = optionalOldOrder.get();

            oldOrder.setOrderPrice(newCustomerOrderDto.getOrderPrice());
            oldOrder.setNumberOfItems(newCustomerOrderDto.getNumberOfItems());
            oldOrder.setSeller(new Seller(newCustomerOrderDto.getSellerId()));
            oldOrder.setCustomer(new Customer(newCustomerOrderDto.getCustomerId()));
            customerOrderRepo.save(oldOrder);

            response.setCustomerOrder(oldOrder);
            response.setMessage("Order Updated");
            return response;
        } else {
            response.setMessage("This order un available");
            return response;
        }
    }

    public OrderResponse getOrderById(Integer orderId) {
        Optional<CustomerOrder> oldOrder = customerOrderRepo.findById(orderId);
        OrderResponse response = new OrderResponse();
        if (oldOrder.isPresent()) {
            response.setCustomerOrder(oldOrder.get());
            response.setMessage("successful");
            return response;

        } else {
            response.setMessage("this id un available");
            return response;
        }
    }

    public FindAllSellerOrdersResponse findAllOrdersBySellerId(Integer sellerId) {
        List<CustomerOrder> oldSellerOrder = customerOrderRepo.findAllOrdersBySellerId(sellerId);
        FindAllSellerOrdersResponse response = new FindAllSellerOrdersResponse();
        if (oldSellerOrder.isEmpty()) {
            response.setMessage("no orders founded");
            return response;
        } else {
            response.setCustomerOrders(oldSellerOrder);
            response.setMessage("Successful");
            return response;
        }
    }

    @Transactional
    public OrderResponse deleteOrderByOrderId(int orderId) {
        OrderResponse response = new OrderResponse();
        Optional<CustomerOrder> oldOrder = customerOrderRepo.findById(orderId);
        if (oldOrder.isPresent()) {
            customerOrderRepo.deleteOrderByOrderId(orderId);
            response.setCustomerOrder(oldOrder.get());
            response.setMessage("order deleted");
            return response;
        } else {
            response.setMessage("this order un available");
            return response;
        }


    }


}























