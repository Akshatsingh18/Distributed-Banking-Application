package com.project.bank.Interface;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bank.model.AccountModel;
import com.project.bank.model.StatementModel;

@Repository
public interface TransactionInterface extends JpaRepository<AccountModel, Long> {
    List<AccountModel> findByCustomerName(String customerName);
}
