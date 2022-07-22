package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class AppConfig {
    @Bean
    public Address getAddress(){
        return new Address("Houston","Texas","USA","77028");
    }
    @Bean
    public Phone getPhone(){
        return new Phone("713-124-1424");
    }
    @Bean
    public Phone getPhone2(){
        return new Phone("713-124-1425");
    }
    @Bean
    public Student getStudent(){
        return new Student();
    }
}
