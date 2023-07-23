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
public class DeliveryAssurance extends User {
    private LocalDateTime joinDate;
    private String userName;
    private String password;

    @OneToMany(mappedBy = "deliveryassurance", cascade = CascadeType.ALL)
    private List<CustomerOrder> orders = new ArrayList<>();

    public DeliveryAssurance(String name, String phoneNumber, String address, String userName, String password,
            LocalDateTime joinDate) {
        this.setName(name);
        this.setAddress(address);
        this.setPhoneNumber(phoneNumber);
        this.userName = userName;
        this.password = password;
        this.joinDate = joinDate;

    }

}
