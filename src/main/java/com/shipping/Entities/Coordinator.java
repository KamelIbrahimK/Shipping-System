package com.shipping.Entities;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Coordinator extends User{
    private String userName;
    private String password;
}
