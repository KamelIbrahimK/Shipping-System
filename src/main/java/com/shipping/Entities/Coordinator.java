package com.shipping.Entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
public class Coordinator extends User {
    private String userName;
    private String password;
    private LocalDateTime joinDate;

    public Coordinator(String name, String phoneNumber, String address, String userName, String password , LocalDateTime joinDate) {
        this.setName(name);
        this.setAddress(address);
        this.setPhoneNumber(phoneNumber);
        this.userName = userName;
        this.password = password;
        this.joinDate = joinDate;
    }
}
