package com.jeramtough.randl3.svcapi.component.sender;

/**
 * Created on 2018-09-15 11:41
 * by @author JeramTough
 */
public enum SendSourceType {
    /**
     * PHONE
     */
    PHONE("phone number"),

    /**
     * EMAIL
     */
    EMAIL("email address");

    private String name;

    SendSourceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public SendSourceType toSendSourceType(String name) {
        if ("phone number".equals(name)) {
            return PHONE;
        }
        else {
            return EMAIL;
        }
    }
}
