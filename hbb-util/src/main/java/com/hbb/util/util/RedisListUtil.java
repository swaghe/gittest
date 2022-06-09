package com.hbb.util.util;

import com.hbb.util.exception.OverflowException;
import com.hbb.util.exception.TimeoutException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author hjc
 * @Date 2022-05-15-22-21
 **/
@Component
public class RedisListUtil extends RedisUtil {


    /**
     * 向列表头部插入
     *
     * @param key   缓存名称
     * @param value 待插入值
     */
    public void lPush(String key, Object value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 设置一个带过期时间的缓存
     *
     * @param key     缓存名称
     * @param value   值
     * @param timeout 过期时间
     * @return 布尔值
     */
    public boolean set(String key, Object value, long timeout) throws TimeoutException {
        if (timeout <= 0) {
            throw new TimeoutException("timeout cannot be less than or equal to 0 ");
        }
        try {
            lPush(key, value);
            expire(key, timeout);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 创建或插入缓存
     *
     * @param key  缓存名称
     * @param list 列表
     */
    public void pushAll(String key, List list) {
        if (list.size() == 0) {
            return;
        }
        redisTemplate.opsForList().leftPushAll(key, list);
    }

    /**
     * 创建或插入缓存，带过期时间
     *
     * @param key     缓存名称
     * @param list    值
     * @param timeout 过期时间
     * @return 布尔值
     */
    public boolean pushAll(String key, List list, long timeout) throws TimeoutException {
        if (timeout <= 0) {
            throw new TimeoutException("timeout cannot be less than or equal to 0 ");
        }
        if (list.size() == 0) {
            return false;
        }
        try {
            pushAll(key, list);
            expire(key, timeout);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 在列表底部插入或新建缓存
     *
     * @param key   缓存名称
     * @param value 值
     */
    public void rPush(String key, Object value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 获取列表指定范围中的值
     *
     * @param key   名称
     * @param start 开始下标
     * @param end   结束下标
     * @return 列表
     */
    public List<Object> lRange(String key, long start, long end) throws OverflowException {
        if (start > end) {
            throw new OverflowException("scope overflow");
        }
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取列表中所有值
     *
     * @param key 缓存名称
     * @return 列表
     */
    public List<Object> getAll(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 列表左边弹出并删除
     *
     * @param key 缓存名称
     * @return 值
     */
    public Object lPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 列表左边弹出count个值并删除
     *
     * @param key   缓存名称
     * @param count 个数
     * @return 列表
     */
    public List<Object> lPop(String key, long count) {
        if (count <= 0) {
            return null;
        }
        return redisTemplate.opsForList().leftPop(key, count);
    }

    /**
     * 列表右边弹出并删除
     *
     * @param key 缓存名称
     * @return 值
     */
    public Object rPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 列表右边弹出count个值并删除
     *
     * @param key   缓存名称
     * @param count 个数
     * @return 列表
     */
    public List<Object> rPop(String key, long count) {
        if (count <= 0) {
            return null;
        }
        return redisTemplate.opsForList().rightPop(key, count);
    }

    /**
     * 获取列表指定下标的值
     *
     * @param key   缓存名称
     * @param index 下标
     * @return 值
     */
    public Object lIndex(String key, long index) {
        if (index < 0) {
            return null;
        }
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 获取列表长度
     *
     * @param key 缓存名称
     * @return 长度
     */
    public long size(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 截取指定区间的值
     *
     * @param key   缓存名称
     * @param start 开始下标
     * @param end   结束下标
     * @return 布尔值
     */
    public boolean trim(String key, long start, long end) throws OverflowException {
        if (start > end) {
            throw new OverflowException("scope overflow");
        }
        try {
            redisTemplate.opsForList().trim(key, start, end);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除列表最后一个元素，将它移动到新的列表中
     *
     * @param src  源列表
     * @param dest 目标列表
     * @return 布尔值
     */
    public boolean rPopLPush(String src, String dest) {
        try {
            redisTemplate.opsForList().rightPopAndLeftPush(src, dest);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据下标更新替换列表中的值
     *
     * @param key   缓存名称
     * @param index 下标
     * @param value 值
     */
    public void update(String key, long index, Object value) {
        if (index < 0) {
            return;
        }
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 移除列表中的元素
     *
     * @param key   缓存名称
     * @param value 元素
     * @return 移除个数
     */
    public long remove(String key, Object value) {
        return remove(key, 1, value);
    }

    /**
     * 移除列表中的count个元素，相同的元素
     *
     * @param key   缓存名称
     * @param count 个数
     * @param value 值
     * @return 移除个数
     */
    public long remove(String key, long count, Object value) {
        if (count <= 0) {
            return -1;
        }
        return redisTemplate.opsForList().remove(key, count, value);
    }

}
