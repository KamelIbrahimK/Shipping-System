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


}
