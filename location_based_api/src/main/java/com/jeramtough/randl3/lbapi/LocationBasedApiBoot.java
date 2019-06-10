package com.jeramtough.randl3.lbapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created on 2019-03-09 16:22
 * by @author JeramTough
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties
public class LocationBasedApiBoot {

    public static void main(String[] args) {
        SpringApplication.run(LocationBasedApiBoot.class, args);
    }

}