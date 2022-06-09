package com.hbb.util.util;

import com.hbb.util.exception.TimeoutException;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @Author hjc
 * @Date 2022-05-15-22-21
 **/
@Component
public class RedisSetUtil extends RedisUtil {

    /**
     * 设置一个缓存可以多个
     *
     * @param key     缓存名称
     * @param objects 值
     * @return 布尔值
     */
    public boolean set(String key, Object... objects) {
        try {
            redisTemplate.opsForSet().add(key, objects);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置一个缓存,带有过期时间
     *
     * @param key     缓存名称
     * @param timeout 过期时间
     * @param objects 值
     * @return 布尔值
     */
    public boolean set(String key, long timeout, Object objects) throws TimeoutException {
        if (timeout <= 0) {
            throw new TimeoutException("timeout cannot be less than or equal to 0 ");
        }
        try {
            set(key, objects);
            expire(key, timeout);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置缓存列表具有事务
     *
     * @param key  缓存名称
     * @param list 列表值
     */
    public void setList(String key, List list) {
        if (list.size() == 0) {
            return;
        }
        redisTemplate.execute(new SessionCallback<List<Object>>() {
            @Override
            public List<Object> execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                for (Object obj : list) {
                    operations.opsForSet().add(key, obj);
                }
                return operations.exec();
            }
        });
    }

    /**
     * 获取所有元素
     *
     * @param key 缓存名称
     * @return 列表
     */
    public Set<Object> get(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 获取缓存长度
     *
     * @param key 缓存名称
     * @return 长度
     */
    public long size(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 移除缓存中的元素
     *
     * @param key   缓存名称
     * @param value 元素
     */
    public void remove(String key, Object value) {
        redisTemplate.opsForSet().remove(key, value);
    }

    /**
     * 随机获取count个列表中的值
     *
     * @param key   缓存名称
     * @param count 个数
     * @return 值
     */
    public Object random(String key, long count) {
        if (count <= 0) {
            return null;
        }
        return redisTemplate.opsForSet().randomMembers(key, count);
    }

    /**
     * 随机获取列表中的一个值
     *
     * @param key 缓存名称
     * @return 值
     */
    public Object random(String key) {
        return random(key, 1);
    }

    /**
     * 判断元素是否在缓存列表中
     *
     * @param key   缓存名称
     * @param value 值
     * @return 布尔值
     */
    public boolean hasMember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 随机弹出一个元素
     *
     * @param key 缓存名称
     * @return 元素
     */
    public Object randPop(String key) {
        return redisTemplate.opsForSet().pop(key);
    }

    /**
     * 获取2个列表中的差集
     *
     * @param key  缓存名称1
     * @param key2 换成名称2
     * @return 差集列表
     */
    public Set<Object> diff(String key, String key2) {
        return redisTemplate.opsForSet().difference(key, key2);
    }

    /**
     * 获取2个缓存列表中的交集
     *
     * @param key  缓存名称1
     * @param key2 缓存名称2
     * @return 交集列表
     */
    public Set<Object> inter(String key, String key2) {
        return redisTemplate.opsForSet().intersect(key, key2);
    }

    /**
     * 获取2个缓存列表中的并集
     *
     * @param key  缓存名称1
     * @param key2 缓存名称2
     * @return 并集列表
     */
    public Set<Object> union(String key, String key2) {
        return redisTemplate.opsForSet().union(key, key2);
    }


}
