package com.shipping.Controllers;

import com.shipping.Dtos.Order.*;
import com.shipping.Entities.CustomerOrder;
import com.shipping.Entities.DeliveryAssurance;
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
    public OrderResponse getOrderById(@RequestParam Integer id){

        return customerOrderService.getOrderById(id);
    }
    @GetMapping(path = "/getAllOrdersBySellerId")
    public FindAllOrdersResponse FindAllSellerOrdersResponse(@RequestParam int sellerId){
        return customerOrderService.findAllOrdersBySellerId(sellerId);
    }
    @GetMapping(path = "/deleteOrderByOrderId")
    public OrderResponse deleteOrderByOrderId(@RequestParam int id){
        return customerOrderService.deleteOrderByOrderId(id);
    }
    @GetMapping(path = "/getAllOrdersByDaId")
    public FindAllOrdersResponse FindAllOrdersResponse(@RequestParam int DaId){
        return customerOrderService.findAllOrdersByDeliveryAssuranceId(DaId);
    }
    @PutMapping(path = "/changeOrderStatus")
    public OrderResponse changeOrderStatus(@RequestBody UpdateOrderStatus order){
        return customerOrderService.updateOrderStatus(order);
    }
    @GetMapping(path = "/getAllOrders")
    public FindAllOrdersResponse getAllOrders(){
        return customerOrderService.getAllOrders();
    }

    @GetMapping(path = "/getOrdersByStatus")
    public FindAllOrdersResponse getOrdersByStatus(@RequestBody FindAllOrdersByOrderStatus orderStatus ){
        return customerOrderService.getOrdersByStatus(orderStatus);
    }
    @GetMapping(path = "/getDeliveryAssuranceByOrderId")
    public DeliveryAssurance getDeliveryAssuranceByOrderId(@RequestParam int orderId){
        return customerOrderService.getDeliveryAssuranceByOrderId(orderId);
    }
    @PostMapping(path = "/assignOrderToDa")
    public OrderResponse assignOrderToDa(@RequestBody AssignOrderDto assignOrderDto){
        return customerOrderService.assignOrderToDa(assignOrderDto);
    }
    @GetMapping(path = "/getSellersNamesByThereNumOfOrders")
    public List<String> getUserNameByOrdersPerDay(@RequestParam Integer numberOfOrders){
        return customerOrderService.getUserNameByOrdersPerDay(numberOfOrders);
    }
    @GetMapping(path = "/deleteAllOrders")
    public String deleteAllOrders(){
        return customerOrderService.deleteAllOrders();
    }
    @GetMapping(path = "/filtterSellersByNumOfOrders")
    public List<String>filterSellersByNumOfOrders(@RequestParam Integer numOfOrders){
        return customerOrderService.filterSellersByNumOfOrders(numOfOrders);
    }
    @GetMapping(path = "/findOrdersCountBySellerId")
    public Integer findOrdersCountBySellerId(@RequestParam Integer sellerId){
        return customerOrderService.findOrdersCountBySellerId(sellerId);
    }

}
