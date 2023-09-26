package com.shipping.Entities;

import com.shipping.Enums.StatementType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Data
public class PriceStatement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String statementName;
    private StatementType statementType;
    private Integer numOfOrders;
    private Integer numOfDays;
    private double value;
}
