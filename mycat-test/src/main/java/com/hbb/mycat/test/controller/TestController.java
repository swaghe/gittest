package com.hbb.mycat.test.controller;


import com.hbb.mycat.test.service.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hjc
 * @since 2022-06-09
 */
@RestController
public class TestController {


    @Autowired
    private TestServiceImpl testService;

    @GetMapping("insert")
    public void insert() throws InterruptedException {
        testService.inserts();
    }
}

