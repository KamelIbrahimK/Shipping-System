package com.shipping.Dtos.Order;

import com.shipping.Enums.OrderStatus;
import lombok.Data;

@Data
public class FindAllOrdersByOrderStatus {

    private OrderStatus orderStatus;
}
