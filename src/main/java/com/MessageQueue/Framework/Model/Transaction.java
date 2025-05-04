package com.MessageQueue.Framework.Model;

import com.MessageQueue.Framework.Utils.MAPPING_TYPE;
import com.MessageQueue.Framework.Utils.TRANSACTION_STATUS;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    private TRANSACTION_STATUS transactionStatus;

    public MAPPING_TYPE getMappingType() {
        return mappingType;
    }

    public void setMappingType(MAPPING_TYPE mappingType) {
        this.mappingType = mappingType;
    }

    private MAPPING_TYPE mappingType;

    private String userData;

    public Transaction(String userData){
        this.userData = userData;
        this.setTransactionStatus(TRANSACTION_STATUS.REST_API_RECIEVED);
        this.id = (long)hashCode();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public TRANSACTION_STATUS getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TRANSACTION_STATUS transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userData, createdAt, mappingType);
    }
}
