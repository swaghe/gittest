package com.example.javahighconcurrency.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.javahighconcurrency.mapper.UserMapper;
import com.example.javahighconcurrency.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
@Slf4j
public class UserService {

    @Resource
    private UserMapper userMapper;


    public void insert() {
        User user = new User();
        String no = UUID.randomUUID().toString().replace("-", "").substring(0, 9);
        user.setNo(no);
        int insert = userMapper.insert(user);
        if (insert < 0) {
            log.info("插入失败");
        }
    }

}
