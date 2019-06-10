package com.jeramtough.randl3.svcapi.component.sender;

import com.jeramtough.jtlog.with.WithLogger;
import com.jeramtough.jtutil.core.DateTimeUtil;

import javax.servlet.http.HttpSession;

/**
 * Created on 2018-09-17 13:02
 * by @author JeramTough
 */
public abstract class BaseVerificationCodeSender implements VerificationCodeSender,
        WithLogger {

    private static final String LAST_SENT_VERIFICATION_CODE_TIME_SESSION_KEY =
            "lastSentVerificationCodeTime";

    protected BaseVerificationCodeSender() {
    }


    @Override
    public boolean send(String verificationCode, String sendingSource, boolean isTest) {
        boolean isSuccessful;
        if (isTest) {
            isSuccessful = true;
        }
        else {
            //sending option.
            isSuccessful = doSending(verificationCode, sendingSource);
        }

        if (isSuccessful) {
            getLogger().verbose("sent the verification code[%s] to " +
                            "[%s] successfully at %s",
                    verificationCode, sendingSource, DateTimeUtil.getCurrentDateTime());
        }
        else {
            getLogger().warn("Failed to sent the verification code[%s] to [%s] at %s, " +
                            "because " +
                            "%s",
                    verificationCode, sendingSource, DateTimeUtil.getCurrentDateTime(),
                    getFailedReason());
        }
        return isSuccessful;
    }

    public abstract boolean doSending(String verificationCode, String sendingSource);

}
