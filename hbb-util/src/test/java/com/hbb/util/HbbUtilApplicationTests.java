package com.hbb.util;

import com.hbb.util.pojo.User;
import com.hbb.util.util.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class HbbUtilApplicationTests {

    @Autowired
    private RedisStringUtil redisStringUtil;

    @Autowired
    private RedisListUtil redisListUtil;

    @Autowired
    private RedisSetUtil redisSetUtil;

    @Autowired
    private RedisHashUtil redisHashUtil;

    @Autowired
    private RedisZSetUtil redisZSetUtil;

    @Test
    void contextLoads() {
//        redisUtil.expire("name",60);
//        System.out.println(redisUtil.getExpire("name"));
//        System.out.println(redisStringUtil.hasKey("name"));
//        System.out.println(redisUtil.del(null));

//        System.out.println(redisStringUtil.set("name2", new User("hbb",18)));
//        System.out.println(redisStringUtil.get("name2"));

//        System.out.println(redisStringUtil.setNx("name3",null,60));

//        Map<String, Object> map = new HashMap<>();
//        map.put("user1",new User("hjc",23));
//        map.put("user2",new User("hjc2",23));
//        map.put("user3",new User("hjc3",23));
//        System.out.println(redisStringUtil.mSet(map));

//        System.out.println(redisStringUtil.mGet("user5","user4"));
//        redisStringUtil.set("count",5);
//        System.out.println(redisStringUtil.decr("count", 2));


//        redisListUtil.lPush("userList",new User("hbb3",23));

//        System.out.println(redisListUtil.lRange("userList2", 2, 3));

//        System.out.println(redisListUtil.lPop("userList"));
//        redisListUtil.update("userList",0,new User("shuai",22));
//        System.out.println(redisListUtil.remove("userList", new User("shuai", 22)));
//        redisListUtil.lPush("testList",new User("3",3));

//        System.out.println(redisListUtil.getAll("testList"));

//        System.out.println(redisSetUtil.set("setList", new User("hbb3", 23)));

//        List<User> list = new ArrayList<>();
//        list.add(new User("uu",1));
//        list.add(new User("uu2",1));
//        redisSetUtil.setList("setList2",list);

//        System.out.println(redisSetUtil.get("setList2"));

//        System.out.println(redisSetUtil.random("setList2",2));
//        redisSetUtil.remove("setList2",new User("uu2",1));

//        System.out.println(redisSetUtil.hasMember("setList2", new User("uu2", 1)));

//        System.out.println(redisSetUtil.randPop("setList2"));

//        System.out.println(redisSetUtil.union("setList","setList2"));

//        redisHashUtil.set("user","name","hbb");
//        redisHashUtil.set("user","age",18);
//        redisHashUtil.set("user","sex","M");

//        System.out.println(redisHashUtil.getAll("user"));

//        System.out.println(redisHashUtil.delValue("user","name","age"));

//        System.out.println(redisHashUtil.decrement("user","age",3));

//        redisZSetUtil.set("score","何嘉城",100);
//        redisZSetUtil.set("score","帅气成",99);
//        redisZSetUtil.set("score","高手城",98);
//        redisZSetUtil.set("score","魅力城",97);
//        redisZSetUtil.set("score","天才城",-100);
//        System.out.println(redisZSetUtil.range("score",0,2));
//        System.out.println(redisZSetUtil.rangeByScoreAndDesc("score",0,98));

//        System.out.println(redisZSetUtil.randomMember("score",2));

//        System.out.println(redisZSetUtil.decrement("score","魅力城",2));

        System.out.println(redisZSetUtil.popMax("score"));

    }


}
