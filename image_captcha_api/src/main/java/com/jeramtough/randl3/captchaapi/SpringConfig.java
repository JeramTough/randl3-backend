package com.jeramtough.randl3.captchaapi;

import com.jeramtough.randl3.apicommon.ApiSpringConfig;
import com.jeramtough.randl3.rediscommon.RedisSpringConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created on 2019-03-28 15:36
 * by @author JeramTough
 */
@Configuration
@Import({RedisSpringConfig.class, ApiSpringConfig.class})
public class SpringConfig {

}
