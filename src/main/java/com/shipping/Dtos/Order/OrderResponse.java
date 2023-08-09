package com.shipping.Dtos.Order;

import com.shipping.Entities.CustomerOrder;
import lombok.Data;

@Data
public class OrderResponse {
    private String message;
    private CustomerOrder customerOrder;
}
