package com.example.Splitwise.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class Group {
    private String id;
    private double amount;
    private User paidBy;
    private List<Split> splits;

    public Group(double amount, User paidBy, List<Split> splits) {
        this.amount = amount;
        this.paidBy = paidBy;
        this.splits = splits;
    }

    public abstract boolean validate();
}
