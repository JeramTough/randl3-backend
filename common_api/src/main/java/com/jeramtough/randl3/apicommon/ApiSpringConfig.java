package com.jeramtough.randl3.apicommon;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.jeramtough.randl3.apicommon.component.ApiFailedMessager;
import com.jeramtough.randl3.apicommon.component.ApiResponseDtoFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Created on 2019-04-10 15:28
 * by @author JeramTough
 */
@Configuration
public class ApiSpringConfig {

    @Bean
    public HttpMessageConverters fastjsonHttpMessageConverter() {
        //定义一个转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //添加fastjson的配置信息 比如 ：是否要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //在转换器中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters((HttpMessageConverter<?>) fastConverter);
    }

    @Bean
    public ApiResponseDtoFactory injectApiResponseDtoFactory() {
        return new ApiResponseDtoFactory();
    }

    @Bean
    public ApiFailedMessager injectApiFailedMessager(Environment environment) {
        return new ApiFailedMessager(environment);
    }

    @Bean("beanValidator")
    public Validator injectBeanValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator;
    }

}
