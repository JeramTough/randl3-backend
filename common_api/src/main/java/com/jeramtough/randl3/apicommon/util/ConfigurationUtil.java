package com.jeramtough.randl3.apicommon.util;

/**
 * Created on 2019-03-28 16:44
 * by @author JeramTough
 */
public class ConfigurationUtil {

    public static String[][] getRedisHosts(String redisHosts) {
        String[] temp = redisHosts.split(",");
        String[][] redisHostsArray = new String[temp.length][2];
        for (int i = 0; i < temp.length; i++) {
            String[] hostAndPort = temp[i].split(":");
            redisHostsArray[i] = hostAndPort;
        }
        return redisHostsArray;
    }
}
