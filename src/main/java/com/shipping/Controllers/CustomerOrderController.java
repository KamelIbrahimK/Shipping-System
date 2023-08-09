package com.shipping.Controllers;

import com.shipping.Dtos.Order.CustomerOrderPostDto;
import com.shipping.Dtos.Order.CustomerOrderUpdateDto;
import com.shipping.Dtos.Order.FindAllSellerOrdersResponse;
import com.shipping.Dtos.Order.OrderResponse;
import com.shipping.Entities.CustomerOrder;
import com.shipping.Services.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "/CustomerOrder")
public class CustomerOrderController {
    @Autowired
    private CustomerOrderService customerOrderService;

    @PostMapping(path = "/saveOrder")
    public List<CustomerOrder> saveOrder(@RequestBody List<CustomerOrderPostDto> orders) {
        return customerOrderService.saveOrder(orders);
    }
    @PutMapping (path = "/updateOrder/{id}")
    public OrderResponse updateOrder(@RequestBody CustomerOrderUpdateDto order ) {
        return customerOrderService.updateOrder(order);
    }
    @GetMapping(path = "/getOrderById")
    public OrderResponse getOrderById(@RequestParam int id){

        return customerOrderService.getOrderById(id);
    }
    @GetMapping(path = "/getAllOrdersBySellerId")
    public FindAllSellerOrdersResponse FindAllSellerOrdersResponse(@RequestParam int sellerId){
        return customerOrderService.findAllOrdersBySellerId(sellerId);
    }
    @GetMapping(path = "/deleteOrderByOrderId")
    public OrderResponse deleteOrderByOrderId(@RequestParam int id){
        return customerOrderService.deleteOrderByOrderId(id);
    }
    

}
