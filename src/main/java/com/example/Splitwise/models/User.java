package com.example.Splitwise.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    public String id;
    public String name;
    public String email;
    public String phone;
}
