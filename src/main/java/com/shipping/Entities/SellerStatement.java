package com.shipping.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Entity
@Data
public class SellerStatement {
    @Id
    private Integer sellerId;
    private Integer statementId;
}
