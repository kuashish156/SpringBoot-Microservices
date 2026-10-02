package com.example.demo.service;


import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserService(){

        System.out.println("service class called ");
    }


    public void getUser(){

        int id = 100;
        String name= "ashish kaumr";
        String address = "mumabi";


        System.out.println("user id is " + id);
        System.out.println("user name is " + name);

        System.out.println("user address is " + address);



    }




}
