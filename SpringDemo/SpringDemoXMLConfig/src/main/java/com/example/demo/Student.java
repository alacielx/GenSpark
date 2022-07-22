package com.example.demo;

import java.util.List;

public class Student
{
    private String name;
    private int id;
    private List<Phone> ph;
    private Address add;
    Student(String name, int id, List<Phone> ph, Address add){
        this.name = name;
        this.id = id;
        this.ph = ph;
        this.add = add;
    }
    public String toString(){
        return String.format("Name: %s\nID: %s\nPhone Numbers: %s\nAddress: %s", name, id, ph.toString(), add.toString());
    }
}
