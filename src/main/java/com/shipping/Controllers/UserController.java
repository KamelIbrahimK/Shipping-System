package com.shipping.Controllers;

import com.shipping.Dtos.UserDto;
import com.shipping.Entities.Customer;
import com.shipping.Entities.User;
import com.shipping.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/Customer")
public class UserController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/signUp")
    public UserDto signup(@RequestBody UserDto userDto) {
        return customerService.signup(userDto);
    }

}
