package ru.clevertec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class HeadlineHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeadlineHubApplication.class, args);
    }

}
