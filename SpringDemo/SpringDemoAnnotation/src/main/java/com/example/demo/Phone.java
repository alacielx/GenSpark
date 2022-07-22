package com.example.demo;

import org.springframework.stereotype.Component;

public class Phone
{
    private String mob;
    Phone(String mobile){
        this.mob = mobile;
    }
    public String toString(){
        return mob;
    }
}