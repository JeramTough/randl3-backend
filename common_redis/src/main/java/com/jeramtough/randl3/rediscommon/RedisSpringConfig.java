package com.jeramtough.randl3.rediscommon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created on 2019-04-10 15:00
 * by @author JeramTough
 */
@Configuration
public class RedisSpringConfig {

    @Bean
    public JedisConnectionFactory redisConnectionFactory(
            @Value("${api.config.redis.host}") String redisHost) {
        String[] hostAndPort = redisHost.split(":");
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(hostAndPort[0],
                Integer.parseInt(hostAndPort[1]));
        return new JedisConnectionFactory(config);
    }

    @Bean("redisTemplate")
    public RedisTemplate injectRedisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        redisTemplate.setDefaultSerializer(stringRedisSerializer);
        return redisTemplate;
    }

    /* @Bean
    Jedis injectJedis(@Value("api.config.redis.hosts") String redisHosts) {

        String[][] redisHostsArray = ConfigurationUtil.getRedisHosts(redisHosts);
        Jedis jedis = null;
        if (redisHostsArray.length == 1) {
            jedis = new Jedis(redisHostsArray[0][0], Integer.parseInt(redisHostsArray[0][1]));
        }
        else {
            for (int i = 0; i < redisHostsArray.length; i++) {
                String host = redisHostsArray[i][0];
                int port = Integer.parseInt(redisHostsArray[i][1]);
                if (i == 0) {
                    jedis = new Jedis(redisHostsArray[0][0],
                            Integer.parseInt(redisHostsArray[0][1]));
                }
                else {
                    jedis.slaveof(redisHostsArray[0][0],
                            Integer.parseInt(redisHostsArray[0][1]));
                }
            }
        }
        return jedis;
    }*/

}
