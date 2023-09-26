package com.shipping.Controllers;

import com.shipping.Services.FinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Financial")
public class FinancialController {
    @Autowired
    private FinancialService financialService;


    @GetMapping(path = "/calculateAllOrdersPriceBySellerId")
    public double calculateAllOrdersPriceBySellerId(@RequestParam Integer sellerId){
        return financialService.calculateAllOrdersPriceBySellerId(sellerId);
    }
    @PutMapping(path = "/checkIfDaHasAchieveHisTarget")
    public void checkIfDaHasAchieveHisTarget(@RequestParam Integer target){
    }
    @PutMapping(path = "/checkIfDaAchievedTheTarget")
    public String checkIfDaAchievedTheTarget(@RequestParam Integer target){
        return financialService.checkIfDaAchievedTheTarget(target);
    }
    @PutMapping(path = "/giveBouns")
    public void giveBouns(){
      financialService.giveBouns();
    }
}
