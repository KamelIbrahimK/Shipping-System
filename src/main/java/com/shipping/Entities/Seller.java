package com.shipping.Entities;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Entity
@Setter
@Getter
public class Seller extends User {
    private Integer ordersPerDay;
    private String userName;
    private String password;
    private LocalDateTime joinDate;

//    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
//    private List<CustomerOrder> orders = new ArrayList<>();

    public Seller(String name, String phoneNumber, String address, String userName, String password,
                  LocalDateTime joinDate) {
        this.setName(name);
        this.setAddress(address);
        this.setPhoneNumber(phoneNumber);
        this.userName = userName;
        this.password = password;
        this.joinDate = joinDate;

    }

    public Seller(Integer sellerId) {
        this.setId(sellerId);
    }
}

