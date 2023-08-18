package com.shipping.Dtos.Order;

import com.shipping.Enums.OrderStatus;
import lombok.Data;

@Data
public class UpdateOrderStatus {

    private Integer orderId;
    private Integer deliveryAssuranceID;
    private OrderStatus orderStatus;

}
