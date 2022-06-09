package com.hbb.util.util;

import com.hbb.util.exception.OverflowException;
import com.hbb.util.exception.TimeoutException;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @Author hjc
 * @Date 2022-05-16-00-00
 **/
@Component
public class RedisZSetUtil extends RedisUtil {

    /**
     * 设置带分数的缓存
     *
     * @param key   缓存名称
     * @param value 值
     * @param score 分数
     */
    public void set(String key, Object value, double score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 设置带分数的缓存，带过期时间
     *
     * @param key     缓存名称
     * @param value   值
     * @param score   分数
     * @param timeout 过期时间
     */
    public void set(String key, Object value, double score, long timeout) throws TimeoutException {
        if (timeout <= 0) {
            throw new TimeoutException("timeout cannot be less than or equal to 0 ");
        }
        try {
            set(key, value, score);
            expire(key, timeout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取缓存中值的分数
     *
     * @param key   缓存名称
     * @param value 值
     * @return 分数
     */
    public Object get(String key, Object value) {
        if (value.equals(null)) {
            return null;
        }
        return redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * 获取多少个缓存中的值
     *
     * @param key   缓存名称
     * @param start 开始
     * @param end   结束
     * @return 列表
     */
    public Set<Object> range(String key, long start, long end) throws OverflowException {
        if (start > end) {
            throw new OverflowException("scope overflow");
        }
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 获取分数范围中的缓存值，从小到大
     *
     * @param key 缓存名称
     * @param min 最小值
     * @param max 最大值
     * @return 集合
     */
    public Set<Object> rangeByScore(String key, double min, double max) throws OverflowException {
        if (min > max) {
            throw new OverflowException("scope overflow");
        }
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    /**
     * 获取分数范围中的缓存值，并携带分数，从小到大
     *
     * @param key 缓存名称
     * @param min 最小值
     * @param max 最大值
     * @return 集合
     */
    public Set<ZSetOperations.TypedTuple<Object>> rangeByScoreWithScores(String key, double min, double max) throws OverflowException {
        if (min > max) {
            throw new OverflowException("scope overflow");
        }
        return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
    }

    /**
     * 删除缓存中值为value的值
     *
     * @param key   缓存名称
     * @param value 值
     */
    public void remove(String key, Object value) {
        redisTemplate.opsForZSet().remove(key, value);
    }

    /**
     * 获取缓存中集合的大小
     *
     * @param key 缓存名称
     * @return 集合个数
     */
    public long size(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    /**
     * 获取分数范围中的缓存值，从大到小
     *
     * @param key 缓存名称
     * @param min 最小值
     * @param max 最大值
     * @return 集合
     */
    public Set<Object> rangeByScoreAndDesc(String key, double min, double max) throws OverflowException {
        if (min > max) {
            throw new OverflowException("scope overflow");
        }
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
    }

    /**
     * 获取分数范围中的缓存值，并携带分数，从大到小
     *
     * @param key 缓存名称
     * @param min 最小值
     * @param max 最大值
     * @return 集合
     */
    public Set<ZSetOperations.TypedTuple<Object>> rangeByScoreAndDescWithScores(String key, double min, double max) throws OverflowException {
        if (min > max) {
            throw new OverflowException("scope overflow");
        }
        return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max);
    }

    /**
     * 随机弹出一个元素
     *
     * @param key 缓存名称
     * @return 值
     */
    public Object randomMember(String key) {
        return randomMember(key, 1);
    }

    /**
     * 随机弹出count个值
     *
     * @param key   缓存名称
     * @param count 个数
     * @return 值
     */
    public List<Object> randomMember(String key, long count) {
        if (count <= 0) {
            return null;
        }
        return redisTemplate.opsForZSet().randomMembers(key, count);
    }

    /**
     * 增加缓存中值的分数
     *
     * @param key   缓存名称
     * @param value 值
     * @param num   增加值
     * @return 增加后的值
     */
    public double increment(String key, Object value, long num) {
        if (num <= 0) {
            return -1;
        }
        return redisTemplate.opsForZSet().incrementScore(key, value, num);
    }

    /**
     * 减少缓存中值的分数
     *
     * @param key   缓存名称
     * @param value 值
     * @param num   减少值
     * @return 减少后的值
     */
    public double decrement(String key, Object value, long num) {
        if (num <= 0) {
            return -1;
        }
        return redisTemplate.opsForZSet().incrementScore(key, value, -num);
    }

    /**
     * 弹出一个缓存中分数最大的值
     *
     * @param key 缓存名称
     * @return
     */
    public Object popMax(String key) {
        return popMax(key, 1);
    }

    /**
     * 弹出count个缓存中分数最大的值
     *
     * @param key   缓存名称
     * @param count 个数
     * @return 弹出的值
     */
    public Set<ZSetOperations.TypedTuple<Object>> popMax(String key, long count) {
        if (count <= 0) {
            return null;
        }
        return redisTemplate.opsForZSet().popMax(key, count);
    }

    /**
     * 弹出一个缓存中分数最小的值
     *
     * @param key 缓存名称
     * @return
     */
    public Object popMin(String key) {
        return popMin(key, 1);
    }

    /**
     * 弹出count个缓存中分数最小的值
     *
     * @param key   缓存名称
     * @param count 个数
     * @return 弹出的值
     */
    public Set<ZSetOperations.TypedTuple<Object>> popMin(String key, long count) {
        if (count <= 0) {
            return null;
        }
        return redisTemplate.opsForZSet().popMin(key, count);
    }

    /**
     * 获取2个集合的差集
     *
     * @param key      集合1
     * @param otherKey 集合2
     * @return 差集
     */
    public Set<Object> diff(String key, String otherKey) {
        return redisTemplate.opsForZSet().difference(key, otherKey);
    }


    /**
     * 获取2个集合的差集，携带分数
     *
     * @param key      集合1
     * @param otherKey 集合2
     * @return 差集
     */
    public Set<ZSetOperations.TypedTuple<Object>> diffWithScores(String key, String otherKey) {
        return redisTemplate.opsForZSet().differenceWithScores(key, otherKey);
    }

    /**
     * 获取2个集合的交集
     *
     * @param key      集合1
     * @param otherKey 集合2
     * @return 交集
     */
    public Set<Object> inter(String key, String otherKey) {
        return redisTemplate.opsForZSet().intersect(key, otherKey);
    }

    /**
     * 获取2个集合的交集，携带分数
     *
     * @param key      集合1
     * @param otherKey 集合2
     * @return 交集
     */
    public Set<ZSetOperations.TypedTuple<Object>> interWithScores(String key, String otherKey) {
        return redisTemplate.opsForZSet().intersectWithScores(key, otherKey);
    }

    /**
     * 获取2个集合的并集
     *
     * @param key      集合1
     * @param otherKey 集合2
     * @return 并集
     */
    public Set<Object> union(String key, String otherKey) {
        return redisTemplate.opsForZSet().union(key, otherKey);
    }

    /**
     * 获取2个集合的并集，携带分数
     *
     * @param key      集合1
     * @param otherKey 集合2
     * @return 并集
     */
    public Set<ZSetOperations.TypedTuple<Object>> unionWithScores(String key, String otherKey) {
        return redisTemplate.opsForZSet().unionWithScores(key, otherKey);
    }

}
