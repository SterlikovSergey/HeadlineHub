package ru.clevertec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableFeignClients(basePackages = "ru.clevertec.client")
public class HeadlineHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeadlineHubApplication.class, args);
    }

}
