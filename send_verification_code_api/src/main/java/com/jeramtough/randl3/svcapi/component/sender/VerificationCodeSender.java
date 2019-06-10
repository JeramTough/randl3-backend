package com.jeramtough.randl3.svcapi.component.sender;

/**
 * Created on 2018-09-13 00:21
 * by @author JeramTough
 */
public interface VerificationCodeSender {

    boolean send(String verificationCode, String sendingSource, boolean isTest);

    String getFailedReason();

}
