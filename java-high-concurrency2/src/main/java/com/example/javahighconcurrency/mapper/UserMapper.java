package com.example.javahighconcurrency.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.javahighconcurrency.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
