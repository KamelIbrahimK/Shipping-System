package com.shipping.Entities;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class DeliveryAssurance extends User{
    private LocalDateTime joinDate;
    private String userName;
    private String password;

    @OneToMany(mappedBy = "deliveryassurance", cascade = CascadeType.ALL)
    private List<CustomerOrder> orders = new ArrayList<>();

}
