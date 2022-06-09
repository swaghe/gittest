package com.example.validator.controller;

import com.example.validator.pojo.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TestController {

    @PostMapping("test")
    public String test(@Valid User user) {
        return "ok";
    }

    @PostMapping("test2") //RequestBodyAdvice 需要请求参数有@RequestBody 才能触发
    public String test2(@RequestBody String name) {
        return "ok";
    }
}
