package com.shipping.Dtos.Order;

import lombok.Data;

@Data
public class TopSeller {
    private int sellerId;
    private int orderCount;
    private String sellerName;

    public TopSeller(int sellerId, int orderCount, String sellerName) {
        this.sellerId = sellerId;
        this.orderCount = orderCount;
        this.sellerName = sellerName;
    }
}
