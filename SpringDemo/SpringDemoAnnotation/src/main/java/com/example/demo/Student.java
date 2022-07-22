package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Student
{
    private String name;
    private int id;
    @Autowired
    private List<Phone> ph;
    @Autowired
    private Address add;
    Student(String name, int id, List<Phone> ph, Address add){
        this.name = name;
        this.id = id;
        this.ph = ph;
        this.add = add;
    }
    Student(){
        this.name = "Alaciel";
        this.id = 12345678;
        this.ph = ph;
        this.add = add;
    }
    public String toString(){
        return String.format("Name: %s\nID: %s\nPhone Numbers: %s\nAddress: %s",
                name,
                id,
                ph.toString(),
                add.toString());
    }
}
