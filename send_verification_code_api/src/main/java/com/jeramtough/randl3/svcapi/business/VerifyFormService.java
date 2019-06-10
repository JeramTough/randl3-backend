package com.jeramtough.randl3.svcapi.business;

import com.jeramtough.jtcomponent.task.response.TaskResponse;
import com.jeramtough.randl3.svcapi.pojo.apiparams.SendVerificationCodeApiForm;

/**
 * Created on 2019-04-12 09:56
 * by @author JeramTough
 */
public interface VerifyFormService {

    /**
     * key 'errorCode' value is Int type
     * key 'residualInterval' is Long type
     *
     * @return {@link TaskResponse}
     */
    TaskResponse verifySendVerificationCodeForm(
            SendVerificationCodeApiForm sendVerificationCodeApiForm);

}
