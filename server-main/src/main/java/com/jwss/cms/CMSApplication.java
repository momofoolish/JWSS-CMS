package com.jwss.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.jwss")
@ImportResource(locations = {"classpath:kaptcha.xml"})
public class CMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(CMSApplication.class, args);
    }

}
