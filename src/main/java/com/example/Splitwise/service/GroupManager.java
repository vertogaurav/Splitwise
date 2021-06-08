package com.example.Splitwise.service;

import com.example.Splitwise.models.Group;
import com.example.Splitwise.models.GroupType;
import com.example.Splitwise.models.Split;
import com.example.Splitwise.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupManager {
    List<Group> groups;
    public Map<String , User> userMap;
    Map<String,Map<String,Double>> balanceSheet;

    public GroupManager() {
        this.groups = new ArrayList<Group>();
        this.userMap = new HashMap<String,User>();
        this.balanceSheet = new HashMap<String,Map<String,Double>>();
    }

    public void addUser(User user)
    {
        userMap.put(user.getId(),user);
        balanceSheet.put(user.getId(),new HashMap<String,Double>());
    }

    public void addGroup(GroupType groupType, double amount, String paidBy, List<Split> splits){

        Group group=GroupService.createGroup(groupType,amount,userMap.get(paidBy),splits);
        groups.add(group);

        for(Split split : group.getSplits())
        {
            String paidTo=split.getUser().getId();
            Map<String ,Double> balances=balanceSheet.get(paidBy);

            if(!balances.containsKey(paidTo))
            {
                balances.put(paidTo,0.0);
            }

            balances.put(paidTo,balances.get(paidTo)+split.getAmount());

            balances=balanceSheet.get(paidTo);
            if (!balances.containsKey(paidBy))
            {
                balances.put(paidBy,0.0);
            }
            balances.put(paidBy,balances.get(paidBy)-split.getAmount());
        }
    }


    public  void showUserBalance(String  userId)
    {
        boolean isEmpty=true;
        for(Map.Entry<String ,Double> userBalance:balanceSheet.get(userId).entrySet())
        {
            if(userBalance.getValue()!=0)
            {
                isEmpty=false;
                printBalance(userId,userBalance.getKey(),userBalance.getValue());
            }
        }
        if (isEmpty)
        {
            System.out.println("NO Balance");
        }
    }

    public void showAllBalance()
    {
        boolean isEmpty=true;
        for(Map.Entry<String ,Map<String,Double>> allBalance:balanceSheet.entrySet() )
        {
            for(Map.Entry<String ,Double> userBalance:allBalance.getValue().entrySet())
            {
                if(userBalance.getValue()>0)
                {
                    isEmpty=false;
                    printBalance(allBalance.getKey(),userBalance.getKey(),userBalance.getValue());
                }
            }
            if (isEmpty)
                System.out.println("NO Balance");
        }
    }
    public  void printBalance(String user1,String user2,double amount)
    {
        String name1=userMap.get(user1).getName();
        String name2=userMap.get(user2).getName();

        if(amount<0)
        {
            System.out.println(name1 +" owes " + name2+" amount "+amount);
        }
        else
            System.out.println(name2+" owes "+ name1 +" amount "+amount);

    }
}
