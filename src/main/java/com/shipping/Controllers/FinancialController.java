package com.shipping.Controllers;

import com.shipping.Services.FinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/Financial")
public class FinancialController {
    @Autowired
    private FinancialService financialService;


    @GetMapping(path = "/calculateAllOrdersPriceBySellerId")
    public double calculateAllOrdersPriceBySellerId(@RequestParam Integer sellerId){
        return financialService.calculateAllOrdersPriceBySellerId(sellerId);
    }
}
