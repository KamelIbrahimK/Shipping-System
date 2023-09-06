package com.shipping.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@NoArgsConstructor
@Entity
@Setter
@Getter
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
    @JoinColumn(name = "delivery_assurance_id")
    private DeliveryAssurance deliveryassurance;



}
