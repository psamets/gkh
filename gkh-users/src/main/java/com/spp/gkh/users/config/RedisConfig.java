package com.spp.gkh.users.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {
    @Value("${com.spp.springbootdemo.redis.host}")
    private String redisHost;
    @Value("${com.spp.springbootdemo.redis.port}")
    private Integer redisPort;

    /*@Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }*/

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        final RedisStandaloneConfiguration standaloneConfig =
                new RedisStandaloneConfiguration(redisHost, redisPort);
        return new JedisConnectionFactory(standaloneConfig);
    }

    @Bean //("genericRestTemplate")
    @Primary
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        //template.setKeySerializer(new JdkSerializationRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        /*template.setValueSerializer(new RedisSerializer<User>() {
            @Override
            public byte[] serialize(User user) throws SerializationException {
                if (user == null) {
                    return new byte[0];
                }
                try {
                    return objectMapper().writeValueAsBytes(user);
                } catch (JsonProcessingException e) {
                    throw new SerializationException("Cannot serialize " + user, e);
                }
            }

            @Override
            public User deserialize(byte[] bytes) throws SerializationException {
                if (bytes == null || bytes.length == 0) {
                    return null;
                }
                try {
                    return objectMapper().readValue(bytes, User.class);
                } catch (IOException e) {
                    throw new SerializationException("Cannot deserialize " + Arrays.toString(bytes), e);
                }
            }
        });*/
        return template;
    }
}
