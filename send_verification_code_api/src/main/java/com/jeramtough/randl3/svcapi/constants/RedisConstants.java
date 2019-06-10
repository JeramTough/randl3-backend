package com.jeramtough.randl3.svcapi.constants;

/**
 * Created on 2019-04-08 16:45
 * by @author JeramTough
 */
public class RedisConstants {

    public static final String SVC_INFO_KEY_PREFIX=
            "send verification code info key:";

    public static final String SVC_INFO_LAST_SENDING_TIME_HASH_KEY="last sent time";

    public static final String SVC_INFO_SENDING_TYPE_HASH_KEY="sending type";

    /**
     * The sending source just is phone number or email address
     */
    public static final String SVC_INFO_SENDING_SOURCE_HASH_KEY="sending source";


}
