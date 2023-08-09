package com.shipping.Dtos.Order;

import lombok.Data;

@Data
public class CustomerOrderUpdateDto {
    private double orderPrice;
    private Integer numberOfItems;
    private Integer sellerId;
    private Integer customerId;
    private Integer orderId;


}
