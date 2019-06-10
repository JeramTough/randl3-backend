package com.jeramtough.randl3.captchaapi.component.imagecage;

import com.jeramtough.randl3.captchaapi.component.ApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Created on 2019-03-28 13:40
 * by @author JeramTough
 */
@Component
@RefreshScope
public class MyImageCageProducer extends ImageCageProducer {

    @Autowired
    public MyImageCageProducer(ApiConfig apiConfig) {
        super(apiConfig.getCaptchaWidth(), apiConfig.getCaptchaHeight(),
                apiConfig.getCaptchaTextScale());
    }
}
