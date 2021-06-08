package com.example.Splitwise.service;

import com.example.Splitwise.models.*;

import java.util.List;

public class GroupService {
    public static Group createGroup(GroupType groupType, double amount,User paidBy, List<Split> splits)
    {
        switch (groupType)
        {
            case EXACT:
                return new ExactGroup(amount, paidBy, splits);
            case EQUAL:
                int totalSplits=splits.size();
                double splitAmount=Math.round(amount/totalSplits);
                for(Split split:splits)
                {
                    split.setAmount(splitAmount);
                }
                return  new EqualGroup(amount, paidBy, splits);
            default:
                return null;
        }
    }
}
