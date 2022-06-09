package com.hbb.util.util;


import com.hbb.util.exception.TimeoutException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * @Author hjc
 * @Date 2022-05-15-22-21
 **/
@Component
public class RedisStringUtil extends RedisUtil {


    /**
     * 设置缓存
     *
     * @param key     缓存名称
     * @param value   缓存值
     * @param timeout 过期时间
     * @return 创建成功返回true，失败返回false
     */
    public boolean setEx(String key, Object value, long timeout) throws TimeoutException {
        if (timeout <= 0) {
            throw new TimeoutException("timeout cannot be less than or equal to 0 ");
        }
        try {
            redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 设置缓存
     *
     * @param key   缓存名称
     * @param value 缓存值
     * @return 创建成功返回true，失败返回false
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 返回缓存
     *
     * @param key 换成名称
     * @return 返回
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置缓存，如果key不存在则创建，如果key存在则创建失败
     *
     * @param key     缓存名称
     * @param value   缓存值
     * @param timeout 过期时间
     * @return 布尔值
     */
    public boolean setNx(String key, Object value, long timeout) throws TimeoutException {
        if (timeout <= 0) {
            throw new TimeoutException("timeout cannot be less than or equal to 0 ");
        }
        return redisTemplate.opsForValue().setIfAbsent(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置多个缓存
     *
     * @param map 缓存集合
     * @return 布尔值
     */
    public boolean mSet(Map<String, Object> map) {
        if (map.size() == 0) {
            return false;
        }
        try {
            redisTemplate.opsForValue().multiSet(map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取多个缓存
     *
     * @param key 缓存名称
     * @return 数组
     */
    public List<Object> mGet(String... key) {
        List<Object> objects = redisTemplate.opsForValue().multiGet((Collection<String>) CollectionUtils.arrayToList(key));
        assert objects != null;
        return objects.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 增长缓存中的值
     *
     * @param key 缓存名称
     * @param num 数值
     * @return 如果返回-1，说明可能数据类型是字符串，必须是integer或者long；
     */
    public long incr(String key, long num) {
        if (num <= 0) {
            return -1;
        }
        try {
            return redisTemplate.opsForValue().increment(key, num);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 减去缓存中的值
     *
     * @param key 缓存名称
     * @param num 数值
     * @return 如果返回-1，说明可能数据类型是字符串，必须是integer或者long；否则则返回对应数值
     */
    public long decr(String key, long num) {
        if (num <= 0) {
            return -1;
        }
        try {
            return redisTemplate.opsForValue().decrement(key, num);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


}
