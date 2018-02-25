package com.horizon.common.dao.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class JedisDataSourceImpl implements JedisDataSource {
	
	 private static final Logger LOG = LoggerFactory.getLogger(JedisDataSourceImpl.class);
	    
	    @Autowired
	    private ShardedJedisPool shardedJedisPool;
	    
	    @Override
	    public ShardedJedis getRedisClient() {
	        ShardedJedis shardJedis = null;
	        try {
			shardJedis = shardedJedisPool.getResource();
	            return shardJedis;
	        } catch (Exception e) {
	            LOG.error("[JedisDS] getRedisClent error:" + e.getMessage());
	            if (null != shardJedis)
	                shardJedis.close();
	        }
	        return null;
	    }

	    @Override
	    public void returnResource(ShardedJedis shardedJedis) {
	        shardedJedis.close();
	    }

	    @Override
	    public void returnResource(ShardedJedis shardedJedis, boolean broken) {
	        shardedJedis.close();
	    }

}
