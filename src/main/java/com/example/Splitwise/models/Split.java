package com.example.Splitwise.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Split {
    public double amount;
    public User user;

    public Split(User user)
    {
        this.user=user;
    }
}
