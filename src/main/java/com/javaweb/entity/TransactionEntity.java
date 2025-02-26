package com.javaweb.entity;

import javax.persistence.*;

@Entity
@Table(name="transaction")
public class TransactionEntity extends BaseEntity {

    @Column(name="note")
    private String note;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "type")
    private TransactionTypeEntity transactionType;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customerid")
    private CustomerEntity customer;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "staffid")
    private UserEntity staff;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public TransactionTypeEntity getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionTypeEntity transactionType) {
        this.transactionType = transactionType;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public UserEntity getStaff() {
        return staff;
    }

    public void setStaff(UserEntity staff) {
        this.staff = staff;
    }
}
