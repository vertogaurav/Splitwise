package com.example.Splitwise.models;

import java.util.List;

public class EqualGroup extends Group{
    public EqualGroup(double amount, User paidBy, List<Split>splits)
    {
        super(amount, paidBy, splits);
    }
    public boolean validate(){
        return true;
    }
}
