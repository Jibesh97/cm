package com.horizon.common.base;

import java.util.List;
import java.util.Set;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.ShardedJedis;

import com.horizon.common.dao.redis.JedisDataSource;
import com.horizon.common.util.SysContextUtil;

@Repository("redisClientDao")
public class RedisClientDao {
	private static Logger log = Logger.getLogger(SysContextUtil.class);

    @Autowired
    private JedisDataSource redisDataSource;

    public void disconnect() {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        shardedJedis.disconnect();
    }

    /**
     * 设置单个值
     * 
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        String result = null;

        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.set(key, value);
        } catch (Exception e) {
            log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }
    /**
     * 删除
     * 
     * @param key
     * @param value
     * @return
     */
    public Long del(String key) {
        Long result = null;

        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.del(key);
        } catch (Exception e) {
            log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }
    /**
     * 根据key取出value，如果key不存在返回null
     * 
     * @param key
     * @return 
     */
    public String get(String key) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = shardedJedis.get(key);

        } catch (Exception e) {
        	log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    public Boolean exists(String key) {
        Boolean result = false;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.exists(key);
        } catch (Exception e) {
            log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    public String type(String key) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.type(key);

        } catch (Exception e) {
        	log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 在某段时间后失效
     * 
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(String key, int seconds) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.expire(key, seconds);

        } catch (Exception e) {
        	log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 在某个时间点失效
     * 
     * @param key
     * @param unixTime
     * @return
     */
    public Long expireAt(String key, long unixTime) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.expireAt(key, unixTime);

        } catch (Exception e) {
        	log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    public Long ttl(String key) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.ttl(key);

        } catch (Exception e) {
        	log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    public boolean setbit(String key, long offset, boolean value) {

        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        boolean result = false;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.setbit(key, offset, value);
        } catch (Exception e) {
        	log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    public boolean getbit(String key, long offset) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        boolean result = false;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;

        try {
            result = shardedJedis.getbit(key, offset);
        } catch (Exception e) {
        	log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    public long setRange(String key, long offset, String value) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        long result = 0;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.setrange(key, offset, value);
        } catch (Exception e) {
        	log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    public String getRange(String key, long startOffset, long endOffset) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        String result = null;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.getrange(key, startOffset, endOffset);

        } catch (Exception e) {
        	log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }
    /**
     * 散列
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hset(String key,String field, String value) {
        Long result = null;

        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.hset(key, field, value);
        } catch (Exception e) {
        	log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }
    /**
     * 批量set
     * @param key
     * @param field
     * @param value
     * @return OK
     */
    public String hmset(String key, Map<String,String> hash) {
        String result = null;

        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.hmset(key, hash);
        } catch (Exception e) {
        	log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }
    
    public String hget(String key,String field) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = shardedJedis.hget(key, field);

        } catch (Exception e) {
        	log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }
    /**
     * 批量get
     * @param key
     * @param field
     * @param value
     * @return  List<String>
     */
    public  List<String> hmget(String key,String... keys) {
        List<String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = shardedJedis.hmget(key,keys);

        } catch (Exception e) {
        	log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }
    
    /**根据key取出整个map，如果key不存在，返回的map.zize == 0
     * hgetall
     * @param key
     * @param field
     * @param value
     * @return  List<String>
     */
    public  Map<String,String> hmgetall(String key) {
    	Map<String,String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = shardedJedis.hgetAll(key);

        } catch (Exception e) {
        	log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }
    public String hkeysStr(String key) {
    	String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = shardedJedis.hkeys(key).toString();

        } catch (Exception e) {
        	log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }
    
    
    public Set<String> hkeys(String key) {
    	Set<String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = shardedJedis.hkeys(key);

        } catch (Exception e) {
        	log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }
    /**
     * 返回哈希表 key 中所有域的值
     * @param key
     * @return List<String>
     */
    public List<String> hvals(String key) {
    	List<String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = shardedJedis.hvals(key);

        } catch (Exception e) {
        	log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }
    /**
     * 集合
     * @author liy
     * @param key
     * @param value
     * @return
     */
    public Long rpush(String key,String... value) {
        Long result = null;

        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.rpush(key, value);
        } catch (Exception e) {
            log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }
    /**
     * 取集合 如果key不存在则list.size==0
     * @param key
     * @param i 开始位置
     * @param j 结束位置
     * start=0，end=-1时 ，表示列出list的所有元素
     * @return
     */
    public List<String> lrange(String key,int i,int j) {
        List<String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = shardedJedis.lrange(key, i, j);

        } catch (Exception e) {
        	log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }
    
    
    /**
     * 有序存储
     * @param key
     * @param score 顺序位
     * @param member
     * @return
     */
    public Long zadd(String key,double score,String member) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = shardedJedis.zadd(key, score, member);

        } catch (Exception e) {
        	log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }
    
    /**
     * 有序
     * @param key
     * @param score 顺序位
     * @param member
     * @return
     */
    public Set<String> zget(String key,long start,long end) {
        Set<String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = shardedJedis.zrange(key, start, end);

        } catch (Exception e) {
        	log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }
    /** 
     * 推入消息到redis消息通道 
     *  
     * @param byte[] 
     *            channel 
     * @param byte[] 
     *            message 
     */  
    public Long publish(String channel, String message) {  
    	log.debug("redis publish "+ message);
    	Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return null;
        }

        boolean broken = false;
        try {
        	Jedis[] jedisArray = new Jedis[]{};
        	jedisArray = shardedJedis.getAllShards().toArray(jedisArray);
        	Jedis jedis = jedisArray[0];
        	result = jedis.publish(channel, message);
        } catch (Exception e) {
        	log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
  
    }  
    

    /** 
     * 监听消息通道 
     * @param jedisPubSub - 监听任务 
     * @param channels - 要监听的消息通道 
     */  
    public void subscribe(JedisPubSub jedisPubSub, String... channels) {  
    	
    	
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return ;
        }

        boolean broken = false;
        try {
        	Jedis[] jedisArray = new Jedis[]{};
        	jedisArray = shardedJedis.getAllShards().toArray(jedisArray);
        	Jedis jedis = jedisArray[0]; 
        	jedis.subscribe(jedisPubSub, channels);  
        } catch (Exception e) {
        	log.error(e.getMessage());
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
    	
    } 
    /** 
     * 监听消息通道   模糊订阅
     * @param jedisPubSub - 监听任务 
     * @param channels - 要监听的消息通道 
     */  
    public void psubscribe(JedisPubSub jedisPubSub, String... channels) {  
    	
    	
    	ShardedJedis shardedJedis = redisDataSource.getRedisClient();
    	if (shardedJedis == null) {
    		return ;
    	}
    	
    	boolean broken = false;
    	try {
    		Jedis[] jedisArray = new Jedis[]{};
    		jedisArray = shardedJedis.getAllShards().toArray(jedisArray);
    		Jedis jedis = jedisArray[0]; 
    		jedis.psubscribe(jedisPubSub, channels);  
    	} catch (Exception e) {
    		log.error(e.getMessage());
    		broken = true;
    	} finally {
    		redisDataSource.returnResource(shardedJedis, broken);
    	}
    	
    } 
    
 public void hdel(String key,String secondKey) {  
    	
    	
    	ShardedJedis shardedJedis = redisDataSource.getRedisClient();
    	if (shardedJedis == null) {
    		return ;
    	}
    	
    	boolean broken = false;
    	try {
    		shardedJedis.hdel(key, secondKey);  
    	} catch (Exception e) {
    		log.error(e.getMessage());
    		broken = true;
    	} finally {
    		redisDataSource.returnResource(shardedJedis, broken);
    	}
    	
    } 
    
}