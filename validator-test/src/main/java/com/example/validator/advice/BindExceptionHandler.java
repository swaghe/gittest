package com.example.validator.advice;

import com.alibaba.fastjson.JSON;
import com.example.validator.pojo.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@RestControllerAdvice
public class BindExceptionHandler {


    @ExceptionHandler(BindException.class)
    public void handleBindException(HttpServletRequest request, HttpServletResponse response, BindException e) throws IOException {
        String errorDate = "";
        for (FieldError error : e.getFieldErrors()) {
            errorDate = error.getDefaultMessage();
        }
        response.setHeader("content-type", "application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(JSON.toJSONString(new Result<>(500, errorDate)));
        writer.flush();
        writer.close();
    }
}
