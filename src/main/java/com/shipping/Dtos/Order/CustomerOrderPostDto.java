package com.shipping.Dtos.Order;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerOrderPostDto {
    private double orderPrice;
    private Integer numberOfItems;
    private Integer sellerId;
    private Integer customerId;


}
