package com.jeramtough.randl3.captchaapi.business;

import com.jeramtough.jtcomponent.task.bean.no.TaskResult;
import com.jeramtough.jtcomponent.task.response.TaskResponse;
import com.jeramtough.jtcomponent.task.response.TaskResponseBuilder;
import com.jeramtough.jtcomponent.task.runnable.SimpleTaskable;
import com.jeramtough.jtcomponent.task.runner.SimpleRunner;
import com.jeramtough.jtlog.with.WithLogger;
import com.jeramtough.randl3.apicommon.business.MyServiceImpl;
import com.jeramtough.randl3.apicommon.component.ApiFailedMessager;
import com.jeramtough.randl3.captchaapi.constants.RedisConstants;
import com.jeramtough.randl3.captchaapi.component.ApiConfig;
import com.jeramtough.randl3.captchaapi.component.imagecage.MyImageCageProducer;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2019-03-13 17:19
 * by @author JeramTough
 */
@Service
public class CaptchaServiceImpl extends MyServiceImpl
        implements CaptchaService, WithLogger {

    private MyImageCageProducer myImageCageProducer;
    private ApiConfig apiConfig;
    private HttpSession httpSession;
    private RedisTemplate redisTemplate;

    @Autowired
    public CaptchaServiceImpl(
            MyImageCageProducer myImageCageProducer,
            ApiConfig apiConfig, HttpSession httpSession,
            RedisTemplate redisTemplate, ApiFailedMessager apiFailedMessager) {

        super(apiFailedMessager);
        this.myImageCageProducer = myImageCageProducer;
        this.apiConfig = apiConfig;
        this.httpSession = httpSession;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public TaskResponse getCaptcha() {
        return TaskResponseBuilder.doing(taskResult -> {

            String randomCode =
                    RandomStringUtils.random(apiConfig.getCaptchaTextLength(), true,
                            true);
            byte[] imageBytes = myImageCageProducer.draw(randomCode);
            taskResult.putPayload("imageBytes", imageBytes);

            //saving the code to Redis
            String key = RedisConstants.IMAGE_CAPTCHA_CODE_KEY_PREFIX + httpSession.getId();
            redisTemplate.opsForValue().set(key, randomCode);
            redisTemplate.expire(key, apiConfig.getCaptchaLiveTime(),
                    TimeUnit.MILLISECONDS);

            getLogger().info(
                    "Created and saved the captcha code(" + randomCode + ") for " +
                            "user(sessionId=" + httpSession.getId() + ")");

            return true;
        });
    }

    @Override
    public TaskResponse verifyCaptcha(String captchaCode) {
        return TaskResponseBuilder.doing(taskResult -> {
            String key = RedisConstants.IMAGE_CAPTCHA_CODE_KEY_PREFIX + httpSession.getId();
            String rightImageCaptchaCode = (String) redisTemplate.opsForValue().get(key);
            boolean isOk = captchaCode.equalsIgnoreCase(rightImageCaptchaCode);

            if (rightImageCaptchaCode == null) {
                putErrorCode(taskResult, 101);
            }
            else if (!isOk) {
                putErrorCode(taskResult, 100);
            }
            return isOk;
        });
    }


}
