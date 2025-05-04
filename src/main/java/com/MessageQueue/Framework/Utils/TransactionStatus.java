package com.MessageQueue.Framework.Utils;

import com.MessageQueue.Framework.Model.Transaction;
import com.MessageQueue.Framework.Services.TransactionService;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class TransactionStatus {

    private static final int MAX_SIZE = 10;
    public static Transaction CurrentTransaction;
    private final TransactionService transactionService;
    private LinkedHashMap<Long, Transaction> transactionMap;

    public TransactionStatus(TransactionService transactionService){
        this.transactionService = transactionService;
        transactionMap = new LinkedHashMap<>(MAX_SIZE);
    }

    public void put(long key, Transaction value){
        CurrentTransaction = value;
        while (transactionMap.size() >= MAX_SIZE){
            StoreInSql(transactionMap.entrySet().iterator().next());
            transactionMap.remove(transactionMap.entrySet().iterator().next().getKey());
        }
        transactionMap.put(key, value);
    }

    void StoreInSql(Map.Entry<Long, Transaction> entry){
        this.transactionService.saveTransaction(entry.getValue());
    }

    public static void SetCurrentTransactionStatus(TRANSACTION_STATUS transactionStatus){
        if(Objects.nonNull(CurrentTransaction)){
            CurrentTransaction.setTransactionStatus(transactionStatus);
        }
    }
}
