package com.jeramtough.randl3.captchaapi.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Created on 2019-03-15 13:38
 * by @author JeramTough
 */
@Component
@RefreshScope
public class ApiConfig {

    @Value("${api.config.captcha.height}")
    private int captchaHeight;

    @Value("${api.config.captcha.width}")
    private int captchaWidth;

    @Value("${api.config.captcha.length}")
    private int captchaTextLength;

    @Value("${api.config.captcha.length}")
    private float captchaTextScale;

    @Value("${api.config.captcha.live.time}")
    private long captchaLiveTime;

    public int getCaptchaHeight() {
        return captchaHeight;
    }

    public void setCaptchaHeight(int captchaHeight) {
        this.captchaHeight = captchaHeight;
    }

    public int getCaptchaWidth() {
        return captchaWidth;
    }

    public void setCaptchaWidth(int captchaWidth) {
        this.captchaWidth = captchaWidth;
    }

    public int getCaptchaTextLength() {
        return captchaTextLength;
    }

    public void setCaptchaTextLength(int captchaTextLength) {
        this.captchaTextLength = captchaTextLength;
    }

    public float getCaptchaTextScale() {
        return captchaTextScale;
    }

    public void setCaptchaTextScale(float captchaTextScale) {
        this.captchaTextScale = captchaTextScale;
    }

    public long getCaptchaLiveTime() {
        return captchaLiveTime;
    }

    public void setCaptchaLiveTime(long captchaLiveTime) {
        this.captchaLiveTime = captchaLiveTime;
    }
}
