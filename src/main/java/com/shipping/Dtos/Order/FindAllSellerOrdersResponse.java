package com.shipping.Dtos.Order;

import com.shipping.Entities.CustomerOrder;
import lombok.Data;

import java.util.List;


@Data
public class FindAllSellerOrdersResponse {
    private String message;
    private List<CustomerOrder> customerOrders;
}
