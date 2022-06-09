package com.hbb.util.util;

import com.hbb.util.exception.TimeoutException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Author hjc
 * @Date 2022-05-15-22-21
 **/
@Component
public class RedisHashUtil extends RedisUtil {

    /**
     * 设置hash类型缓存
     *
     * @param hash  缓存名称
     * @param key   字段
     * @param value 字段值
     */
    public void set(String hash, String key, Object value) {
        if (key.equals(null) || hash.equals(null)) {
            return;
        }
        redisTemplate.opsForHash().put(hash, key, value);
    }

    /**
     * 设置hash类型缓存,带过期时间
     *
     * @param hash    缓存名称
     * @param key     字段
     * @param value   字段值
     * @param timeout 过期时间
     */
    public void set(String hash, String key, Object value, long timeout) throws TimeoutException {
        if (timeout <= 0) {
            throw new TimeoutException("timeout cannot be less than or equal to 0 ");
        }
        if (key.equals(null) || hash.equals(null)) {
            return;
        }
        try {
            set(hash, key, value);
            expire(hash, timeout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置hash类型缓存
     *
     * @param hash 缓存名称
     * @param map  map集合
     */
    public void set(String hash, Map map) {
        if (map.size() == 0) {
            return;
        }
        redisTemplate.opsForHash().putAll(hash, map);
    }

    /**
     * 设置hash类型缓存，带过期时间
     *
     * @param hash    缓存名称
     * @param map     map集合
     * @param timeout 过期时间
     */
    public void set(String hash, Map map, long timeout) throws TimeoutException {
        if (map.size() == 0) {
            return;
        }
        if (timeout <= 0) {
            throw new TimeoutException("timeout cannot be less than or equal to 0 ");
        }
        try {
            set(hash, map);
            expire(hash, timeout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取缓存字段值
     *
     * @param hash 缓存名称
     * @param key  字段名称
     * @return 字段值
     */
    public Object getValue(String hash, String key) {
        if (key.equals(null)) {
            return null;
        }
        return redisTemplate.opsForHash().get(hash, key);
    }

    /**
     * 获取缓存多个字段值
     *
     * @param hash 缓存名称
     * @param key  字段们
     * @return 字段值们
     */
    public List getValues(String hash, String... key) {
        return redisTemplate.opsForHash().multiGet(hash, (Collection<Object>) CollectionUtils.arrayToList(key));
    }

    /**
     * 获取缓存中所有字段的值
     *
     * @param hash 缓存名称
     * @return 字段值
     */
    public List getAll(String hash) {
        return redisTemplate.opsForHash().values(hash);
    }

    /**
     * 删除缓存中的字段
     *
     * @param hash 缓存名称
     * @param key  一个或多个字段
     * @return 删除数
     */
    public long delValue(String hash, String... key) {
        return redisTemplate.opsForHash().delete(hash, key);
    }

    /**
     * 返回缓存中的大小
     *
     * @param hash 缓存名称
     * @return 大小
     */
    public long size(String hash) {
        return redisTemplate.opsForHash().size(hash);
    }

    /**
     * 判断缓存中是否有该字段
     *
     * @param hash 缓存名称
     * @param key  字段
     * @return 布尔值
     */
    public boolean exist(String hash, String key) {
        if (key.equals(null)) {
            return false;
        }
        return redisTemplate.opsForHash().hasKey(hash, key);
    }

    /**
     * 增加缓存中字段的值
     *
     * @param hash 缓存名称
     * @param key  字段
     * @param num  增加数
     * @return 增加后结果
     */
    public long increment(String hash, String key, long num) {
        if (num <= 0) {
            return -1;
        }
        try {
            return redisTemplate.opsForHash().increment(hash, key, num);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 减少缓存中字段的值
     *
     * @param hash 缓存名称
     * @param key  字段
     * @param num  减少数
     * @return 减少后结果
     */
    public long decrement(String hash, String key, long num) {
        if (num <= 0) {
            return -1;
        }
        try {
            return redisTemplate.opsForHash().increment(hash, key, -num);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
