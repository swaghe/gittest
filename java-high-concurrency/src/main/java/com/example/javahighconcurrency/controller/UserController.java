package com.example.javahighconcurrency.controller;

import com.example.javahighconcurrency.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {


    @Resource
    private UserService userService;


//    @GetMapping("insert")
//    public void insert(){
//        userService.insert();
//    }
}
