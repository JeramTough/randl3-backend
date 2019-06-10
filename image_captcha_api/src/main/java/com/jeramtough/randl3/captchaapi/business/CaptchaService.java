package com.jeramtough.randl3.captchaapi.business;

import com.jeramtough.jtcomponent.task.response.TaskResponse;

/**
 * Created on 2019-03-13 17:06
 * by @author JeramTough
 */
public interface CaptchaService {

    /**
     * 键imageBytes取到byte[]类型的图片数据
     * @return {@link TaskResponse}
     */
    TaskResponse getCaptcha();

    /**
     * 如果验证失败，键errorCode取到int类型的接口错误码
     *
     * @param captchaCode 用户输入的图片验证码
     * @return {@link TaskResponse}
     */
    TaskResponse verifyCaptcha(String captchaCode);
}
