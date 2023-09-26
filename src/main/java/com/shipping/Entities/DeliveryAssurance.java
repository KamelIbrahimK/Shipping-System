package com.shipping.Entities;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@NoArgsConstructor
@Entity
@Data
public class DeliveryAssurance extends User {
    private LocalDateTime joinDate;
    private String userName;
    private String password;
    private Double salary;

//    @OneToMany(mappedBy = "deliveryassurance", cascade = CascadeType.ALL)
//    private List<CustomerOrder> orders = new ArrayList<>();


//    public DeliveryAssurance(LocalDateTime joinDate, String userName, String password, double salary) {
//        this.joinDate = joinDate;
//        this.userName = userName;
//        this.password = password;
//        this.salary = salary;
//    }

    public DeliveryAssurance(String name, String phoneNumber, String address, String userName, String password, LocalDateTime now, double salary) {
    }
}
