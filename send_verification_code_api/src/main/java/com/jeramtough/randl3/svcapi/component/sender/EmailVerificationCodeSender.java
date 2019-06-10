package com.jeramtough.randl3.svcapi.component.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * Created on 2018-09-13 01:20
 * by @author JeramTough
 */
@Component
public class EmailVerificationCodeSender extends BaseVerificationCodeSender
        implements VerificationCodeSender {

    @Override
    public boolean doSending(String verificationCode, String sendingSource) {
        return false;
    }

    @Override
    public String getFailedReason() {
        return "还没写实现";
    }


}
