package com.example.validator.controller;

import com.example.validator.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {


    @GetMapping("/error")
    public Result error() {
        return new Result(500, "数据出错！");
    }
}
