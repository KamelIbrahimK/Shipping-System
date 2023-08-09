package com.shipping.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@NoArgsConstructor
@Entity

@Data
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private String status;
    private LocalDateTime creationDate=LocalDateTime.now();
    private LocalDateTime completeDate;
    private double orderPrice;
    private double shippingFee;
    private Integer numberOfItems;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "delivery_assurance_Id")
    private DeliveryAssurance deliveryassurance;



}
