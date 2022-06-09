package com.example.validator.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class User {

    @NotNull(message = "用户名不能为空！")
    @Length(min = 0, max = 10, message = "用户名长度不符合要求！")
    private String name;

    @NotNull(message = "用户名不能为空！")
    @Email(message = "邮件格式不符合要求！")
    private String email;

    //    @NotEmpty(message = "年龄不能为空！")
    @Max(value = 99, message = "年龄不符合要求！")
    @Min(value = 18, message = "年龄不符合要求！")
    private Integer age;
}
