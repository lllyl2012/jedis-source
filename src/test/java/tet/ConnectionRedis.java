package tet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class ConnectionRedis {
	AbstractApplicationContext ac;
	ShardedJedisPool jp;
	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("spring-mvc.xml");
		jp = ac.getBean("shardedJedisPool",ShardedJedisPool.class);
	}
	@After
	public void destory() {
		if(null != ac) {
			ac.close();
		}
	}
	@Test
	public void getRedis() {
		ShardedJedis shardedJedis = jp.getResource();
		shardedJedis.set("hahaha", "11111");
		System.out.println(shardedJedis.get("hahaha"));
		jp.returnResource(shardedJedis);
	}
}
