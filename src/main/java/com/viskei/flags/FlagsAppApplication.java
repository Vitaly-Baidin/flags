package com.viskei.flags;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FlagsAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlagsAppApplication.class, args);
    }

}
