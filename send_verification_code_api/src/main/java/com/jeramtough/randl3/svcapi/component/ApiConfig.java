package com.jeramtough.randl3.svcapi.component;

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

    @Value("${api.config.per.sending.interval}")
    private long perSendingInterval;

    @Value("${api.config.verification.code.live.time}")
    private long verificationCodeLiveTime;

    @Value("${api.config.verification.code.length}")
    private int verificationCodeLength;

    public long getPerSendingInterval() {
        return perSendingInterval;
    }

    public void setPerSendingInterval(long perSendingInterval) {
        this.perSendingInterval = perSendingInterval;
    }

    public long getVerificationCodeLiveTime() {
        return verificationCodeLiveTime;
    }

    public void setVerificationCodeLiveTime(long verificationCodeLiveTime) {
        this.verificationCodeLiveTime = verificationCodeLiveTime;
    }

    public int getVerificationCodeLength() {
        return verificationCodeLength;
    }

    public void setVerificationCodeLength(int verificationCodeLength) {
        this.verificationCodeLength = verificationCodeLength;
    }
}
