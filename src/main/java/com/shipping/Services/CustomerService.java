package com.shipping.Services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shipping.Dtos.UserDto;
import com.shipping.Entities.Coordinator;
import com.shipping.Entities.DeliveryAssurance;
import com.shipping.Entities.Seller;
import com.shipping.Enums.UserType;
import com.shipping.repositories.CoordinatorRepo;
import com.shipping.repositories.DeliveryAssuranceRepo;
import com.shipping.repositories.SellerRepo;

@Service
public class CustomerService {
    @Autowired
    private SellerRepo sellerRepo;
    @Autowired
    private DeliveryAssuranceRepo deliveryAssuranceRepo;
    @Autowired
    private CoordinatorRepo coordinatorRepo;

    public boolean checkIfUserNameExists(String userName) {
        Seller seller = sellerRepo.findByUserName(userName);
        DeliveryAssurance deliveryAssurance = deliveryAssuranceRepo.findByUserName(userName);
        Coordinator coordinator = coordinatorRepo.findByUserName(userName);

        if (seller != null || deliveryAssurance != null || coordinator != null) {
            return false;

        }
        return true;

    }

    public boolean checkPasswordValidations(String password) {

        if (password.length() >= 10) {
            for (int i = 0; i < password.length(); i++) {
                if (Character.isUpperCase(password.charAt(i))) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }

    }
    // 1. check username is not exist before
    // 2. check password is at least 10 letters and at least one of them is
    // capital
    // 3. save

    public String signup(UserDto userDto) {

        boolean isExist = checkIfUserNameExists(userDto.getUserName());
        // isExist == false
        if (!isExist) {
            return "Failed!! this username is already exist";
        }
        boolean isValidPassword = checkPasswordValidations(userDto.getPassword());
        if (!isValidPassword) {
            return "Failed!! Password must be equal or more than 10 letters with 1 capital letter";
        }

        if (userDto.getType().equals(UserType.SELLER)) {
            Seller seller = new Seller(userDto.getName(), userDto.getPhoneNumber(), userDto.getAddress(),
                    userDto.getUserName(), userDto.getPassword(), LocalDateTime.now());
            sellerRepo.save(seller);

        } else if (userDto.getType().equals(UserType.DELIVERY_ASSURANCE)) {
            DeliveryAssurance deliveryAssurance = new DeliveryAssurance(userDto.getName(), userDto.getPhoneNumber(),
                    userDto.getAddress(), userDto.getUserName(), userDto.getPassword(), LocalDateTime.now());
            deliveryAssuranceRepo.save(deliveryAssurance);

        } else if (userDto.getType().equals(UserType.COORDINATOR)) {
            Coordinator coordinator = new Coordinator(userDto.getName(), userDto.getPhoneNumber(), userDto.getAddress(),
                    userDto.getUserName(), userDto.getPassword(), LocalDateTime.now());
            coordinatorRepo.save(coordinator);

        }
        return "you're signed up successfully !!";

    }

}
