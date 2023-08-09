package com.shipping.Dtos;

import com.shipping.Enums.UserType;
import lombok.Data;

@Data
public class LoginResponse {
    private String Message;
    private Integer userId;

}
