package com.jeramtough.randl3.configcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created on 2018-09-15 22:36
 * by @author JeramTough
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigCenterBoot {

    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterBoot.class, args);
    }
}
