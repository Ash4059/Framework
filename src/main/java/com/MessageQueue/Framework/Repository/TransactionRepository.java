package com.MessageQueue.Framework.Repository;

import com.MessageQueue.Framework.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> getTransactionById(Long id);

}
