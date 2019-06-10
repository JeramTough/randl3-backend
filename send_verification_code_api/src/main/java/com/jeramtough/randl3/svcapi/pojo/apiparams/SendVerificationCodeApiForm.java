package com.jeramtough.randl3.svcapi.pojo.apiparams;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeramtough.randl3.svcapi.component.sender.SendSourceType;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

/**
 * Created on 2019/4/11 15:01
 * by @author JeramTough
 */
public class SendVerificationCodeApiForm {

    @Pattern(
            regexp = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|" +
                    "(18[0-9])|(19[8|9]))\\d{8}$", message = "200")
    private String phoneNumber;

    @Email(message = "201")
    private String emailAddress;

    private Boolean isTest;

    private transient SendSourceType sendSourceType;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Boolean getIsTest() {
        return isTest;
    }

    public Boolean isTest() {
        return isTest;
    }

    public void setIsTest(Boolean test) {
        isTest = test;
    }

    public void setTest(Boolean test) {
        isTest = test;
    }

    public SendSourceType getSendSourceType() {
        return sendSourceType;
    }

    public void setSendSourceType(
            SendSourceType sendSourceType) {
        this.sendSourceType = sendSourceType;
    }

    @Override
    public String toString() {
        return "SendVerificationCodeApiForm{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", isTest=" + isTest +
                '}';
    }
}
