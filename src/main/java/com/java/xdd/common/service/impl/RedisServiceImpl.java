package com.java.xdd.common.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import com.java.xdd.common.service.RedisService;

import java.util.List;
import java.util.Set;

@Service("redisService")
public class RedisServiceImpl implements RedisService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired(required = false)
    private ShardedJedisPool shardedJedisPool;

    @Override
    public String get(String key) {
        ShardedJedis shardedJedis = null;
        try {
            // 从连接池中获取到jedis分片对象
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭，检测连接是否有效，有效则放回到连接池中，无效则重置状态
            if (null != shardedJedis) shardedJedis.close();
        }
        return null;
    }

    @Override
    public String set(String key,String value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.set(key,value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) shardedJedis.close();
        }
        return null;
    }

    @Override
    public String set(String key, String value, Integer seconds) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            String str = shardedJedis.set(key, value);
            shardedJedis.expire(key , seconds);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) shardedJedis.close();
        }
        return null;
    }

    @Override
    public Long append(String key, String value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.append(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) shardedJedis.close();
        }
        return null;
    }

    @Override
    public Long expire(String key, Integer seconds) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.expire(key,seconds);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) shardedJedis.close();
        }
        return null;
    }

    @Override
    public Long del(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) shardedJedis.close();
        }
        return null;
    }

    @Override
    public Set<String> smembers(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.smembers(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) shardedJedis.close();
        }
        return null;
    }

    @Override
    public Long sadd(String key, String... value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.sadd(key,value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) shardedJedis.close();
        }
        return null;
    }

    @Override
    public Long srem(String key, String value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.srem(key,value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) shardedJedis.close();
        }
        return null;
    }

    @Override
    public Boolean sismember(String key, String value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.sismember(key,value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) shardedJedis.close();
        }
        return null;
    }

    @Override
    public Boolean exists(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) shardedJedis.close();
        }
        return null;
    }

    @Override
    public Long ttl(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.ttl(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) shardedJedis.close();
        }
        return null;
    }

    @Override
    public Long persist(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.persist(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) shardedJedis.close();
        }
        return null;
    }

    @Override
    public Long lpush(String key, String... value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.lpush(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) shardedJedis.close();
        }
        return null;
    }

    @Override
    public List<String> lrange(String key, Integer start, Integer end) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.lrange(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) shardedJedis.close();
        }
        return null;
    }

    @Override
    public Long lrem(String key, Integer count, String value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.lrem(key, count, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) shardedJedis.close();
        }
        return null;
    }

    @Override
    public String ltrim(String key, long start, long end) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.ltrim(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) shardedJedis.close();
        }
        return null;
    }

    @Override
    public Long llen(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.llen(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) shardedJedis.close();
        }
        return null;
    }

    @Override
    public String lindex(String key, long index) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.lindex(key,index);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) shardedJedis.close();
        }
        return null;
    }

}
