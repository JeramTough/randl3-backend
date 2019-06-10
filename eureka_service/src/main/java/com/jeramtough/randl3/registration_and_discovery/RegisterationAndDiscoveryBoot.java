package com.jeramtough.randl3.registration_and_discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created on 2018-09-15 22:36
 * by @author JeramTough
 */
@EnableEurekaServer
@SpringBootApplication
public class RegisterationAndDiscoveryBoot {

    public static void main(String[] args) {
        SpringApplication.run(RegisterationAndDiscoveryBoot.class, args);
    }
}
