package com.shipping.Services;

import com.shipping.Entities.CustomerOrder;
import com.shipping.Entities.DeliveryAssurance;
import com.shipping.Entities.PriceStatement;
import com.shipping.Entities.Seller;
import com.shipping.Enums.StatementType;
import com.shipping.repositories.CustomerOrderRepo;
import com.shipping.repositories.DeliveryAssuranceRepo;
import com.shipping.repositories.SellerRepo;
import com.shipping.repositories.StatementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class FinancialService {
    @Autowired
    private CustomerOrderRepo customerOrderRepo;
    @Autowired
    private SellerRepo sellerRepo;
    @Autowired
    private DeliveryAssuranceRepo deliveryAssuranceRepo;
    @Autowired
    private StatementRepo statementRepo;


    public double calculateAllOrdersPriceBySellerId(Integer sellerId) {
        List<CustomerOrder> sellerOrders = customerOrderRepo.findAllOrdersBySellerId(sellerId);
        Optional<Seller> seller = sellerRepo.findById(sellerId);
        double ordersPrice = 0.0;
        if (seller.isPresent()) {
            for (int i = 0; i <= sellerOrders.size(); i++) {
                ordersPrice += sellerOrders.get(i).getOrderPrice();
            }
            return ordersPrice;
        }
        return ordersPrice;
    }

    public Integer findOrdersCountByDaId(Integer daId) {
        List<CustomerOrder> daOrders = customerOrderRepo.findAllOrdersByDeliveryassuranceId(daId);
        return daOrders.size();
    }

    public Integer findOrdersCountByDaIDAndCompeleteDate(Integer daId, LocalDateTime completeDateStarting) {
        List<CustomerOrder> daOrders = customerOrderRepo.findAllOrdersByDeliveryassuranceId(daId);
        List<CustomerOrder> result = new ArrayList<>();
        for (int i = 0; i < daOrders.size(); i++) {
            if (daOrders.get(i).getCompleteDate().isAfter(completeDateStarting)) {
                result.add(daOrders.get(i));
            }
        }
        return result.size();
    }

    public void checkIfDaHasAchieveHisTarget(Integer target) {
        List<DeliveryAssurance> allDas = deliveryAssuranceRepo.findAll();
        for (int i = 0; i < allDas.size(); i++) {
            double bouns = (allDas.get(i).getSalary()) / 10;
            int daId = allDas.get(i).getId();
            int daOrdersCount = findOrdersCountByDaId(daId);
            if (daOrdersCount >= target) {
                double daSalary = (allDas.get(i).getSalary()) + bouns;
                allDas.get(i).setSalary(daSalary);
            }
        }
    }

    public String checkIfDaAchievedTheTarget(Integer target) {
        List<DeliveryAssurance> allDas = deliveryAssuranceRepo.findAll();
        for (int i = 0; i < allDas.size(); i++) {
            int daId2 = allDas.get(i).getId();
            int daOrdersCount2 = findOrdersCountByDaId(daId2);
            if (daOrdersCount2 >= target) {
                double bouns = (allDas.get(i).getSalary()) / 10;
                allDas.get(i).setSalary((allDas.get(i).getSalary()) + bouns);
                deliveryAssuranceRepo.save(allDas.get(i));

            }
        }
        return "bouns added";
    }

    public void giveBouns() {
        List<PriceStatement> priceStatements = statementRepo.findByStatementType(StatementType.BOUNS);
        if (!priceStatements.isEmpty()) {
            List<DeliveryAssurance> allDas = deliveryAssuranceRepo.findAll();
            for (int j = 0; j < priceStatements.size(); j++) {
                LocalDateTime startingTime= LocalDateTime.now().minusDays(priceStatements.get(j).getNumOfDays());

                for (int i = 0; i < allDas.size(); i++) {
                    int daId = allDas.get(i).getId();
                    int daOrdersCount = findOrdersCountByDaIDAndCompeleteDate(daId,startingTime);
                    if (daOrdersCount >= priceStatements.get(j).getNumOfOrders()) {
                        double netSalary = allDas.get(i).getSalary();
                        double value = priceStatements.get(j).getValue();
                        allDas.get(i).setSalary(netSalary + value);
                        deliveryAssuranceRepo.save(allDas.get(i));
                    }
                }


            }


        }
    }

}
