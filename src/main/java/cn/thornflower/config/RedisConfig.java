/**  
 * Project Name:ThornflowerOCR  
 * File Name:RedisConfig.java  
 * Package Name:cn.thornflower.config  
 * Date:2019年10月23日下午5:20:31  
 * Copyright (c) 2019
 *  
*/  
  
package cn.thornflower.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**  
 * ClassName:RedisConfig <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2019年10月23日 下午5:20:31 <br/>  
 * @author   李景云  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
@Configuration
public class RedisConfig {

     
 @Bean
 @SuppressWarnings("all")
 public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

     RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();

     template.setConnectionFactory(factory);

     Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

     ObjectMapper om = new ObjectMapper();

     om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

     om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

     jackson2JsonRedisSerializer.setObjectMapper(om);

     StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

     // key采用String的序列化方式

     template.setKeySerializer(stringRedisSerializer);

     // hash的key也采用String的序列化方式

     template.setHashKeySerializer(stringRedisSerializer);

     // value序列化方式采用jackson

     template.setValueSerializer(jackson2JsonRedisSerializer);

     // hash的value序列化方式采用jackson

     template.setHashValueSerializer(jackson2JsonRedisSerializer);

     template.afterPropertiesSet();

     return template;

    }
}
  
