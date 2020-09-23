package com.xzx.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//import java.text.SimpleDateFormat;
//import java.util.Calendar;

/**
 * <p>
 * Redis工具类
 * </p>
 */
@Component
public class RedisUtil {
	@Resource
    private StringRedisTemplate stringRedisTemplate;
    
    @Value("${custom.redisExpiredTime}")
    private String redisExpiredTime;

    /**
     * 删除 Redis 中的某个key
     *
     * @param key 键
     */
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * 批量删除 Redis 中的某些key
     *
     * @param keys 键列表
     */
    public void delete(Collection<String> keys) {
        stringRedisTemplate.delete(keys);
    }
    
    public void add(Map<String, Object> map)
    {
//    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    	
//    	Calendar calendarNow=Calendar.getInstance();
//    	System.out.println("初始化redis当前系统时间: "+df.format(calendarNow.getTime()));
    	
    	ValueOperations<String, String> valueOperations=stringRedisTemplate.opsForValue();
    	
    	if(stringRedisTemplate.hasKey(map.get("key").toString()))
    	{
    		stringRedisTemplate.expire(map.get("key").toString(),Long.parseLong(redisExpiredTime),TimeUnit.SECONDS);
    	
//    		Calendar calendar=Calendar.getInstance();
//    		calendar.add(Calendar.SECOND,Integer.parseInt(Long.toString(stringRedisTemplate.getExpire(map.get("key").toString()))));
//    		
//    		System.out.println("key值为"+map.get("key").toString()+"的过期时间:"+df.format(calendar.getTime()));
    	}
    	else
    	{    		
    		valueOperations.set(map.get("key").toString(), map.get("value").toString(),Long.parseLong(redisExpiredTime),TimeUnit.SECONDS);
//    		
//    		Calendar calendar=Calendar.getInstance();
//    		calendar.add(Calendar.SECOND,Integer.parseInt(Long.toString(stringRedisTemplate.getExpire(map.get("key").toString()))));
//    		
//    		System.out.println("key值为"+map.get("key").toString()+"的过期时间:"+df.format(calendar.getTime()));
    	}
    }
    
    public Boolean isHaveKey(String key)
    {
    	return stringRedisTemplate.hasKey(key);
    }
    
    public String getValue(String key)
    {
    	return stringRedisTemplate.opsForValue().get(key);
    }
}
