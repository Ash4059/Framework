package com.MessageQueue.Framework.Utils;

import com.MessageQueue.Framework.Model.Transaction;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAOP {

    private final TransactionStatus transactionStatus;

    public LoggingAOP(TransactionStatus transactionStatus){
        this.transactionStatus = transactionStatus;
    }

    @Before("execution(* com.MessageQueue.Framework.Controller.UserController.*(..)) && " +
            " args(.., userAgent)")
    public void InitTransaction(String userAgent) {
        Transaction transaction = new Transaction(userAgent);
        transactionStatus.put(transaction.getId(), transaction);
    }

    @Before("@annotation(@org.springframework.kafka.annotation.KafkaListener)")
    public void beforeKafkaListenerExecution() {
        TransactionStatus.SetCurrentTransactionStatus(TRANSACTION_STATUS.RECIVED_FROM_KAFKA);
    }
}