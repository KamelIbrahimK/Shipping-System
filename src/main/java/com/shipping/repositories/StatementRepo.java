package com.shipping.repositories;

import com.shipping.Entities.PriceStatement;
import com.shipping.Enums.StatementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementRepo extends JpaRepository<PriceStatement,Integer> {
    List<PriceStatement> findByStatementType(StatementType statementType);
}
