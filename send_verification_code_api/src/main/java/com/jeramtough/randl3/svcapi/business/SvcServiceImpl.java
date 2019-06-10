package com.jeramtough.randl3.svcapi.business;

import com.jeramtough.jtcomponent.task.response.TaskResponse;
import com.jeramtough.jtcomponent.task.response.TaskResponseBuilder;
import com.jeramtough.randl3.apicommon.business.MyServiceImpl;
import com.jeramtough.randl3.apicommon.component.ApiFailedMessager;
import com.jeramtough.randl3.svcapi.component.ApiConfig;
import com.jeramtough.randl3.svcapi.component.sender.EmailVerificationCodeSender;
import com.jeramtough.randl3.svcapi.component.sender.SmsVerificationCodeSender;
import com.jeramtough.randl3.svcapi.component.sender.VerificationCodeSender;
import com.jeramtough.randl3.svcapi.constants.RedisConstants;
import com.jeramtough.randl3.svcapi.pojo.apiparams.SendVerificationCodeApiForm;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * Created on 2019-04-12 09:54
 * by @author JeramTough
 */
@Service
public class SvcServiceImpl extends MyServiceImpl implements SvcService {

    private ApplicationContext applicationContext;
    private HttpSession httpSession;
    private RedisTemplate redisTemplate;
    private ApiConfig apiConfig;

    @Autowired
    public SvcServiceImpl(ApplicationContext applicationContext,
                          HttpSession httpSession,
                          RedisTemplate redisTemplate,
                          ApiConfig apiConfig,
                          ApiFailedMessager apiFailedMessager) {
        super(apiFailedMessager);
        this.applicationContext = applicationContext;
        this.httpSession = httpSession;
        this.redisTemplate = redisTemplate;
        this.apiConfig = apiConfig;
    }

    @Override
    public TaskResponse sendVerificationCode(
            SendVerificationCodeApiForm sendVerificationCodeApiForm) {
        return TaskResponseBuilder.doing(taskResult -> {
            VerificationCodeSender sender = null;
            String sendingSource = "";
            switch (sendVerificationCodeApiForm.getSendSourceType()) {
                case PHONE:
                    sender = applicationContext.getBean(SmsVerificationCodeSender.class);
                    sendingSource = sendVerificationCodeApiForm.getPhoneNumber();
                    break;
                case EMAIL:
                    sender = applicationContext.getBean(EmailVerificationCodeSender.class);
                    sendingSource = sendVerificationCodeApiForm.getEmailAddress();
                    break;
                default:
            }
            sender = Objects.requireNonNull(sender);

            String verificationCode =
                    RandomStringUtils.randomNumeric(apiConfig.getVerificationCodeLength());
            boolean isSuccessful = sender.send(verificationCode, sendingSource,
                    sendVerificationCodeApiForm.isTest());

            String redisKey = RedisConstants.SVC_INFO_KEY_PREFIX + httpSession.getId();
            redisTemplate.opsForHash().put(redisKey,
                    RedisConstants.SVC_INFO_LAST_SENDING_TIME_HASH_KEY,
                    String.valueOf(System.currentTimeMillis()));
            redisTemplate.opsForHash().put(redisKey,
                    RedisConstants.SVC_INFO_SENDING_TYPE_HASH_KEY,
                    sendVerificationCodeApiForm.getSendSourceType().getName());
            redisTemplate.opsForHash().put(redisKey,
                    RedisConstants.SVC_INFO_SENDING_SOURCE_HASH_KEY,
                    sendingSource);
            if (!isSuccessful) {
                super.putErrorCode(taskResult, 206, sender.getFailedReason());
            }

            return isSuccessful;
        });
    }
}
