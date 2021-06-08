package com.example.Splitwise.models;

import java.util.List;

public class ExactGroup extends Group{
    public ExactGroup(double amount, User paidBy, List<Split> splits)
    {
        super(amount, paidBy, splits);
    }
    @Override
    public boolean validate()
    {

        double totalAmount=getAmount();
        double sumSplitAmount=0;
        for(Split split:getSplits())
        {
            ExactSplit exactSplit=(ExactSplit) split;
            sumSplitAmount+=exactSplit.getAmount();
        }
        if(totalAmount!=sumSplitAmount)
            return false;

        return true;
    }
}
