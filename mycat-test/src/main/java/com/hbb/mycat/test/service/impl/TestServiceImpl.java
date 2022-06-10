package com.hbb.mycat.test.service.impl;

import com.hbb.mycat.test.pojo.Test;
import com.hbb.mycat.test.mapper.TestMapper;
import com.hbb.mycat.test.service.TestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbb.mycat.test.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hjc
 * @since 2022-06-09
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {


    @Autowired
    private TestMapper testMapper;

    public static final int corePoolSize = 60;

    public static final int maximumPoolSize = 100;


    @Autowired
    private IdGenerator idGenerator;


    public void inserts() throws InterruptedException {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(60, 100
                , 60L, TimeUnit.SECONDS, new ArrayBlockingQueue(100000));
        CopyOnWriteArrayList<Test> list = new CopyOnWriteArrayList<>();
        CountDownLatch count = new CountDownLatch(100000);
        for (int i = 0; i < 100000; i++) {
            poolExecutor.execute(() -> {

                String c2 = UUID.randomUUID().toString().substring(0, 15).replace("-", "");
                Test test = new Test(idGenerator.snowflakeId() + "", c2);
                list.add(test);

                count.countDown();
            });
        }
        poolExecutor.shutdown();
        count.await();
        this.saveBatch(list, list.size());


    }

    public void dev() {
        System.out.println("haha");
    }
}
