package com.jeramtough.randl3.svcapi.business;

import com.jeramtough.jtcomponent.task.bean.no.TaskResult;
import com.jeramtough.jtcomponent.task.response.TaskResponse;
import com.jeramtough.jtcomponent.task.response.TaskResponseBuilder;
import com.jeramtough.jtcomponent.task.runnable.SimpleTaskable;
import com.jeramtough.jtcomponent.task.runner.SimpleRunner;
import com.jeramtough.randl3.apicommon.business.MyServiceImpl;
import com.jeramtough.randl3.apicommon.component.ApiFailedMessager;
import com.jeramtough.randl3.svcapi.component.ApiConfig;
import com.jeramtough.randl3.svcapi.component.sender.SendSourceType;
import com.jeramtough.randl3.svcapi.constants.RedisConstants;
import com.jeramtough.randl3.svcapi.pojo.apiparams.SendVerificationCodeApiForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created on 2019-04-12 09:56
 * by @author JeramTough
 */
@Service
public class VerifyFormServiceImpl extends MyServiceImpl implements VerifyFormService {

    private Validator validator;
    private HttpSession httpSession;
    private RedisTemplate redisTemplate;
    private ApiConfig apiConfig;

    @Autowired
    public VerifyFormServiceImpl(
            @Qualifier("beanValidator") Validator validator,
            HttpSession httpSession,
            RedisTemplate redisTemplate,
            ApiConfig apiConfig, ApiFailedMessager apiFailedMessager) {
        super(apiFailedMessager);

        this.validator = validator;
        this.httpSession = httpSession;
        this.redisTemplate = redisTemplate;
        this.apiConfig = apiConfig;
    }

    @Override
    public TaskResponse verifySendVerificationCodeForm(
            SendVerificationCodeApiForm sendVerificationCodeApiForm) {
        return TaskResponseBuilder.doing(taskResult -> {
            Set<ConstraintViolation<SendVerificationCodeApiForm>> constraintViolations =
                    validator.validate(sendVerificationCodeApiForm);
            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<SendVerificationCodeApiForm> violation : constraintViolations) {
                    putErrorCode(taskResult, Integer.parseInt(violation.getMessage()));
                    return false;
                }
            }


            //Email and phone number all are null or the Email and the phone number all are not null
            boolean emailAndPhoneAllAreNull =
                    sendVerificationCodeApiForm.getEmailAddress() == null
                            && sendVerificationCodeApiForm.getPhoneNumber() == null;
            boolean emailAndPhoneAllAreNotNull = sendVerificationCodeApiForm.getEmailAddress() != null
                    && sendVerificationCodeApiForm.getPhoneNumber() != null;
            if (emailAndPhoneAllAreNull || emailAndPhoneAllAreNotNull) {
                putErrorCode(taskResult, 203);
                return false;
            }

            //判断距离下次可发送时间还有多久
            String redisKey = RedisConstants.SVC_INFO_KEY_PREFIX + httpSession.getId();
            if (redisTemplate.hasKey(redisKey) != null && redisTemplate.hasKey(
                    redisKey)) {
                long lastSendingTime =Long.parseLong( (String) redisTemplate.opsForHash().get(redisKey,
                        RedisConstants.SVC_INFO_LAST_SENDING_TIME_HASH_KEY));
                long interval = System.currentTimeMillis() - lastSendingTime;
                if (interval < apiConfig.getPerSendingInterval()) {
                    long residualInterval = (apiConfig.getPerSendingInterval()-interval)/1000;
                    putErrorCode(taskResult, 202, residualInterval);
                    return false;
                }
            }

            //set the sending source type
            if (sendVerificationCodeApiForm.getPhoneNumber() != null) {
                taskResult.putPayload("sendSourceType", SendSourceType.PHONE);
            }
            else {
                taskResult.putPayload("sendSourceType", SendSourceType.EMAIL);
            }
            return true;
        });
    }
}
