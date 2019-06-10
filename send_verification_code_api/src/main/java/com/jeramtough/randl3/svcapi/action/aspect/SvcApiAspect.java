package com.jeramtough.randl3.svcapi.action.aspect;

import com.jeramtough.jtcomponent.task.response.TaskResponse;
import com.jeramtough.randl3.apicommon.action.controller.MyAction;
import com.jeramtough.randl3.apicommon.component.ApiResponseDtoFactory;
import com.jeramtough.randl3.apicommon.pojo.ApiResponseDTO;
import com.jeramtough.randl3.svcapi.business.VerifyFormService;
import com.jeramtough.randl3.svcapi.component.sender.SendSourceType;
import com.jeramtough.randl3.svcapi.pojo.apiparams.SendVerificationCodeApiForm;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Objects;

/**
 * Created on 2018-09-12 19:22
 * by @author JeramTough
 */
@Aspect
@Controller
public class SvcApiAspect extends MyAction {

    private VerifyFormService verifyFormService;

    @Autowired
    public SvcApiAspect(
            ApiResponseDtoFactory apiResponseDtoFactory,
            VerifyFormService verifyFormService) {
        super(apiResponseDtoFactory);
        this.verifyFormService = verifyFormService;
    }

    @Around("execution(public com.jeramtough.randl3.apicommon.pojo.ApiResponseDTO " +
            "com.jeramtough.randl3.svcapi.action.controller" +
            ".SvcController.sendVerificationCode(..))")
    public ApiResponseDTO holdSendVerificationCodeApi(ProceedingJoinPoint joinPoint) {
        //get SendVerificationCodeApiForm instance from args
        SendVerificationCodeApiForm sendVerificationCodeApiForm = null;
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof SendVerificationCodeApiForm) {
                sendVerificationCodeApiForm = (SendVerificationCodeApiForm) arg;
                break;
            }
        }
        sendVerificationCodeApiForm = Objects.requireNonNull(sendVerificationCodeApiForm);

        TaskResponse taskResponse =
                verifyFormService.verifySendVerificationCodeForm(sendVerificationCodeApiForm);

        if (!taskResponse.getTaskResult().isSuccessful()) {
            return getApiResponseDtoFactory().getFailedApiResponseDTO(taskResponse);
        }
        try {
            SendSourceType sendSourceType =
                    (SendSourceType) taskResponse.getTaskResult().getSerializablePayload(
                            "sendSourceType");
            sendVerificationCodeApiForm.setSendSourceType(sendSourceType);
            ApiResponseDTO apiResponseDTO = (ApiResponseDTO) joinPoint.proceed();
            return apiResponseDTO;
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    //*****************

}
