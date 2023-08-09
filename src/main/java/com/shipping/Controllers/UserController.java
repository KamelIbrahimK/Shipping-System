package com.shipping.Controllers;

import com.shipping.Dtos.LoginDto;
import com.shipping.Dtos.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.shipping.Dtos.UserDto;
import com.shipping.Services.CustomerService;

@RestController
@RequestMapping(path = "/User")
public class UserController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/signUp")
    public String signup(@RequestBody UserDto userDto) {
        return customerService.signup(userDto);
    }


    @PostMapping(path = "/login")
    public LoginResponse login(@RequestBody LoginDto loginDto)
    {return customerService.login(loginDto);}

}
