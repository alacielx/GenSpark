package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
public class Student
{
    private String name;
    private int id;
    @Autowired
    private List<Phone> ph;
    @Autowired
    private Address add;
//    Student(String name, int id, List<Phone> ph, Address add){
//        this.name = name;
//        this.id = id;
//        this.ph = ph;
//        this.add = add;
//    }
    Student(){
        this.name = "Alaciel";
        this.id = 12345678;
    }
    public String toString(){
        return String.format(
                "Name: %s\n" +
                "ID: %s\n" +
                "Phone Numbers: %s\n" +
                "Address: %s",
                name, id, ph, add);
    }
}
