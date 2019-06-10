package com.jeramtough.randl3.svcapi.business;

import com.jeramtough.jtcomponent.task.response.TaskResponse;
import com.jeramtough.randl3.svcapi.pojo.apiparams.SendVerificationCodeApiForm;

/**
 * Created on 2019-04-12 09:49
 * by @author JeramTough
 */
public interface SvcService {

    TaskResponse sendVerificationCode(SendVerificationCodeApiForm sendVerificationCodeApiForm);

}
