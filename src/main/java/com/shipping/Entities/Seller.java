package com.shipping.Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Seller extends User {
    private Integer ordersPerDay;
    private String userName;
    private String password;
    private LocalDateTime joinDate;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<CustomerOrder> orders = new ArrayList<>();

    public Seller(String name, String phoneNumber, String address, String userName, String password,
            LocalDateTime joinDate) {
        this.setName(name);
        this.setAddress(address);
        this.setPhoneNumber(phoneNumber);
        this.userName = userName;
        this.password = password;
        this.joinDate = joinDate;

    }

}
