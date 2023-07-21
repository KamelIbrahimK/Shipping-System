package com.shipping.Entities;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Seller extends User {
    private Integer ordersPerDay;
    private String userName;
    private String password;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<CustomerOrder> orders = new ArrayList<>();

}
