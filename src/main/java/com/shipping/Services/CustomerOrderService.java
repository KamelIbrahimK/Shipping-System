package com.shipping.Services;

import com.shipping.Dtos.Order.*;
import com.shipping.Entities.Customer;
import com.shipping.Entities.CustomerOrder;
import com.shipping.Entities.DeliveryAssurance;
import com.shipping.Entities.Seller;
import com.shipping.repositories.CustomerOrderRepo;
import com.shipping.repositories.CustomerRepo;
import com.shipping.repositories.DeliveryAssuranceRepo;
import com.shipping.repositories.SellerRepo;
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
    @Autowired
    private DeliveryAssuranceRepo deliveryAssuranceRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private SellerRepo sellerRepo;


    public List<CustomerOrder> saveOrder(List<CustomerOrderPostDto> orders) {
        // list of db models to hit the db once
        List<CustomerOrder> customerOrders = new ArrayList<>();
        CustomerOrder customerOrder = new CustomerOrder();
        OrderResponse response=new OrderResponse();

        for (int i = 0; i < orders.size(); i++) {
            Customer customer2=customerRepo.findCustomerByCusromerId(orders.get(i).getCustomerId());
            Seller seller=sellerRepo.findSellerBySellerId(orders.get(i).getSellerId());
            if (customer2!=null) {

                if (seller!= null) {
                    customerOrder.setOrderPrice(orders.get(i).getOrderPrice());
                    customerOrder.setNumberOfItems(orders.get(i).getNumberOfItems());
                    customerOrder.setSeller(new Seller(orders.get(i).getSellerId()));
                    customerOrder.setCustomer(new Customer(orders.get(i).getCustomerId()));

                    customerOrders.add(customerOrder);
                    response.setCustomerOrder(customerOrder);
                    response.setMessage("order add  !!");
                }
                else
                {
                    response.setMessage("there is wrong seller Id");

                }
            }else {
                response.setMessage("there is wrong customer Id");

            }
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

    public FindAllOrdersResponse findAllOrdersBySellerId(Integer sellerId) {
        List<CustomerOrder> oldSellerOrder = customerOrderRepo.findAllOrdersBySellerId(sellerId);
        FindAllOrdersResponse response = new FindAllOrdersResponse();
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
            customerOrderRepo.deleteById(orderId);
            response.setCustomerOrder(oldOrder.get());
            response.setMessage("order deleted");
            return response;
        } else {
            response.setMessage("this order un available");
            return response;
        }


    }

    public FindAllOrdersResponse findAllOrdersByDeliveryAssuranceId(Integer DaId) {
        List<CustomerOrder> DaOrders = customerOrderRepo.findAllOrdersByDeliveryassuranceId(DaId);
        FindAllOrdersResponse response = new FindAllOrdersResponse();
        if (DaOrders.isEmpty()) {
            response.setMessage("no orders founded");
            return response;
        } else {
            response.setCustomerOrders(DaOrders);
            response.setMessage("Successful");
            return response;
        }


    }

    public OrderResponse updateOrderStatus(UpdateOrderStatus orderStatus) {
        Optional<CustomerOrder> DaOrder = customerOrderRepo.findById(orderStatus.getOrderId());
        OrderResponse orderResponse = new OrderResponse();
        if (DaOrder.isPresent() && orderStatus.getDeliveryAssuranceID() == DaOrder.get().getDeliveryassurance().getId()) {
            DaOrder.get().setStatus(orderStatus.getOrderStatus().name());
            customerOrderRepo.save(DaOrder.get());
            orderResponse.setCustomerOrder(DaOrder.get());
            orderResponse.setMessage("order status updated successfully");
        } else {
            orderResponse.setMessage("No order Founded");
        }
        return orderResponse;
    }

    public FindAllOrdersResponse getAllOrders() {
        FindAllOrdersResponse orders = new FindAllOrdersResponse();
        orders.setCustomerOrders(customerOrderRepo.findAll());
        if (orders.getCustomerOrders().isEmpty()) {
            orders.setMessage("there is no orders available");
            return orders;
        } else {
            orders.setCustomerOrders(orders.getCustomerOrders());
            orders.setMessage("there is your orders");
            return orders;
        }

    }

    public FindAllOrdersResponse getOrdersByStatus(FindAllOrdersByOrderStatus orderStatus) {
        List<CustomerOrder> ordersWithSameStatus = customerOrderRepo.getOrdersByStatus(orderStatus.getOrderStatus().name());
        FindAllOrdersResponse ordersResponse = new FindAllOrdersResponse();
        if (ordersWithSameStatus.isEmpty()) {
            ordersResponse.setMessage("there is no orders available ");
            return ordersResponse;
        } else {
            ordersResponse.setCustomerOrders(ordersWithSameStatus);
            ordersResponse.setMessage("there is your orders");
            return ordersResponse;
        }
    }

    public DeliveryAssurance getDeliveryAssuranceByOrderId(Integer orderId) {
        Optional<CustomerOrder> order = customerOrderRepo.findById(orderId);
        if (order.isPresent()) {
            if (order.get().getDeliveryassurance().getId() != null) {
                return order.get().getDeliveryassurance();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public OrderResponse assignOrderToDa(AssignOrderDto assignOrderDto) {
        OrderResponse response = new OrderResponse();
        Optional<CustomerOrder> order = customerOrderRepo.findById(assignOrderDto.getOrderId());
        if (order.isPresent()) {
            if (order.get().getDeliveryassurance().getId() != null) {
                response.setCustomerOrder(order.get());
                response.setMessage("this order is already assigned");
            } else {
                Optional<DeliveryAssurance> da = deliveryAssuranceRepo.findById(assignOrderDto.getDeliveryAssuranceId());
                if (da.isPresent()) {
                    order.get().setDeliveryassurance(da.get());
                    customerOrderRepo.save(order.get());
                    response.setCustomerOrder(order.get());
                    response.setMessage("you have been assign this order to " + da.get().getUserName());

                } else {
                    response.setMessage("Delivery Assurance is not exist");

                }
            }
        } else {
            response.setMessage("this order id unavailable");

        }
        return response;
    }
}



























