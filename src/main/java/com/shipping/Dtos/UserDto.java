package com.shipping.Dtos;

import com.shipping.Enums.UserType;
import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String phoneNumber;
    private String address;
    private String userName;
    private String password;
    private UserType type;
    private double salary;

    public UserDto(String name, String phoneNumber, String address, String userName, String password, UserType type, double salary) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userName = userName;
        this.password = password;
        this.type = type;
        this.salary = salary;
    }
}
