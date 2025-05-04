package com.MessageQueue.Framework.Services;

import com.MessageQueue.Framework.Model.Transaction;
import com.MessageQueue.Framework.Repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public Optional<Transaction> getTransactionById(Long id){
        return this.transactionRepository.getTransactionById(id);
    }

    public void saveTransaction(Transaction transaction){
        this.transactionRepository.save(transaction);
    }

}
