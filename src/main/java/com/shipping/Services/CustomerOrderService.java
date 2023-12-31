package com.shipping.Services;

import com.shipping.Dtos.Order.*;
import com.shipping.Entities.Customer;
import com.shipping.Entities.CustomerOrder;
import com.shipping.Entities.DeliveryAssurance;
import com.shipping.Entities.Seller;
import com.shipping.Enums.OrderStatus;
import com.shipping.repositories.CustomerOrderRepo;
import com.shipping.repositories.CustomerRepo;
import com.shipping.repositories.DeliveryAssuranceRepo;
import com.shipping.repositories.SellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.*;


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
        OrderResponse response = new OrderResponse();

        for (int i = 0; i < orders.size(); i++) {
            Optional<Customer> customer2 = customerRepo.findById(orders.get(i).getCustomerId());
            Optional<Seller> seller = sellerRepo.findById(orders.get(i).getSellerId());
            if (customer2.isPresent()) {

                if (seller.isPresent()) {
                    customerOrder.setOrderPrice(orders.get(i).getOrderPrice());
                    customerOrder.setNumberOfItems(orders.get(i).getNumberOfItems());
                    customerOrder.setSeller(new Seller(orders.get(i).getSellerId()));
                    customerOrder.setCustomer(new Customer(orders.get(i).getCustomerId()));

                    customerOrders.add(customerOrder);
                    response.setCustomerOrder(customerOrder);
                    response.setMessage("order add  !!");
                } else {
                    response.setMessage("there is wrong seller Id");

                }
            } else {
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
        int id=orderStatus.getOrderId();
        Boolean x= customerOrderRepo.existsById(orderStatus.getOrderId());
        Optional<CustomerOrder> DaOrder = customerOrderRepo.findById(orderStatus.getOrderId());
        OrderResponse orderResponse = new OrderResponse();
        if (DaOrder.isPresent() && orderStatus.getDeliveryAssuranceID()
                == DaOrder.get().getDeliveryassurance().getId()) {
            DaOrder.get().setStatus(orderStatus.getOrderStatus().name());
            if(orderStatus.getOrderStatus().equals(OrderStatus.DELIVERED)){
                DaOrder.get().setCompleteDate(LocalDateTime.now());
            }
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

    public List<String> getUserNameByOrdersPerDay(Integer numberOfOrders) {
        List<CustomerOrder> customerOrderList = customerOrderRepo.findAll();
        List<String> sellersName = new ArrayList<>();
        for (int i = 0; i < customerOrderList.size(); i++) {
            if (customerOrderList.get(i).getSeller().getOrdersPerDay() >= numberOfOrders) {
                String sellerName = customerOrderList.get(i).getSeller().getName();
                sellersName.add(sellerName);
            }
        }
        return sellersName;

    }

    public String deleteAllOrders() {
        List<CustomerOrder> customerOrders = customerOrderRepo.findAll();
        if (customerOrders.isEmpty()) {
            return "there is no orders founded";
        } else {
            customerOrderRepo.deleteAll();
        }
        return "orders deleted";
    }

    public Integer findOrdersCountBySellerId(Integer sellerId) {
        List<CustomerOrder> sellerOrders = customerOrderRepo.findAllOrdersBySellerId(sellerId);
        return sellerOrders.size();
    }

    public List<String> filterSellersByNumOfOrders(Integer numOfOrders) {
        List<String> sellersName = new ArrayList<>();
        List<Seller> sellers = sellerRepo.findAll();
        for (int i = 0; i < sellers.size(); i++) {
            int sellerOrdersCount = findOrdersCountBySellerId(sellers.get(i).getId());
            if (sellerOrdersCount >= numOfOrders) {
                sellersName.add(sellers.get(i).getName());
            }
        }
        return sellersName;
    }

    public static void sort(List<TopSeller> list) {

        list.sort((o1, o2)
                -> o2.getOrderCount() -
                o1.getOrderCount());
    }

    public List<String> findTheTopSellersInOrdersCount(Integer limit) {
        List<String> topSellers = new ArrayList<>();
        List<Seller> sellers = sellerRepo.findAll();
        List<TopSeller> AllSellersWithOrdersCount = new ArrayList<>();
        for (int i = 0; i < sellers.size(); i++) {
            int sellerId = sellers.get(i).getId();
            int sellerOrdersCount = findOrdersCountBySellerId(sellerId);
            AllSellersWithOrdersCount.add(new TopSeller(sellerId, sellerOrdersCount, sellers.get(i).getName()));
        }
        sort(AllSellersWithOrdersCount);
        for (int i = 0; i < AllSellersWithOrdersCount.size(); i++) {
            if (i == limit) {
                break;
            }
            topSellers.add(i, AllSellersWithOrdersCount.get(i).getSellerName());

        }
        return topSellers;
    }

}


































