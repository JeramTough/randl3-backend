package com.jeramtough.randl3.svcapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created on 2019-03-13 16:32
 * by @author JeramTough
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties
public class SvcApiBoot {

    public static void main(String[] args) {
        SpringApplication.run(SvcApiBoot.class, args);
    }

}
