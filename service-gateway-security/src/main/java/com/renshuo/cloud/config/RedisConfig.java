package com.renshuo.cloud.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.renshuo.cloud.util.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @description: redis的配置类
 * @author: renshuo
 * @date: 2023/8/7
 */
@Configuration
public class RedisConfig {

    @Bean
    @SuppressWarnings(value = {"unchecked", "rawtypes"})
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        template.setConnectionFactory(connectionFactory);
        FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        // Hash的key也采用StringRedisSerializer的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();


        //创建字符串序列化
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer(); //创建json序列化
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        //配置json 序列化
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//        //key 使用string序列化
//        template.setKeySerializer(stringRedisSerializer);
//        // value 使用json序列化
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//        // hash 的key使用string序列化
//        template.setHashKeySerializer(stringRedisSerializer);
//        // hash value 使用json序列化
//        template.setHashValueSerializer(jackson2JsonRedisSerializer);
//
//        template.afterPropertiesSet();


        //创建字符串序列化
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer(); //创建json序列化
//
//        GenericFastJsonRedisSerializer genericFastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
//
//        //key 使用string序列化
//        template.setKeySerializer(stringRedisSerializer);
//        // value 使用json序列化
//        template.setValueSerializer(genericFastJsonRedisSerializer);
//        // hash 的key使用string序列化
//        template.setHashKeySerializer(stringRedisSerializer);
//        // hash value 使用json序列化
//        template.setHashValueSerializer(genericFastJsonRedisSerializer);
//
//        template.afterPropertiesSet();

        return template;
    }
}
