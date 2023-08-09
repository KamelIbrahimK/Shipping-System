package com.shipping.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
public class Customer extends User {
//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
//    private List<CustomerOrder> orders = new ArrayList<>();

    public Customer(int id) {
        this.setId(id);
    }

}
