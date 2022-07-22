package com.example.demo;

import org.springframework.stereotype.Component;

public class Address
{
    private String city;
    private String state;
    private String country;
    private String zipcode;
    Address(String city, String state, String country, String zipcode){
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
    }
    public String toString(){
        return String.format("%s, %s, %s, %s",city,state,country,zipcode);
    }
}
