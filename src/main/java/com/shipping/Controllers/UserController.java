package com.shipping.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shipping.Dtos.UserDto;
import com.shipping.Services.CustomerService;

@RestController
@RequestMapping(path = "/Customer")
public class UserController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/signUp")
    public String signup(@RequestBody UserDto userDto) {
        return customerService.signup(userDto);
    }

}
