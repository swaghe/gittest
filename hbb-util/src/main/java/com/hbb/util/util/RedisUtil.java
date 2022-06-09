package com.hbb.util.util;


import com.hbb.util.exception.TimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.concurrent.TimeUnit;


/**
 * @Author hjc
 * @Date 2022-05-15-22-21
 **/
@Component
public class RedisUtil {

    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;

    /**
     * 指定缓存失效时间
     *
     * @param key     缓存健
     * @param timeout 过期时间
     * @return 是否设置缓存成功
     */
    public boolean expire(String key, long timeout) throws TimeoutException {
        if (timeout <= 0) {
            throw new TimeoutException("timeout cannot be less than or equal to 0 ");
        }
        try {
            redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取缓存过期时间
     *
     * @param key 缓存名称
     * @return -1，代表没有缓存没有过期时间； -2 代表缓存已经过期或缓存不存在；
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 获取缓存是否存在
     *
     * @param key 缓存名称
     * @return 存在返回true，反之返回false；
     */
    public boolean hasKey(String key) {
        if (key == null) {
            return false;
        }
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除一个或多个缓存
     *
     * @param key 缓存名称
     * @return 返回-1，代表传入空字符串；返回0，代表删除0个；返回值大于0，说明删除对应个数。
     */
    public long del(String... key) {
        if (key == null) {
            return -1;
        }
        if (key.length == 1) {
            Boolean delete = redisTemplate.delete(key[0]);
            return delete ? 1 : 0;
        } else {
            return redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
        }
    }

}




