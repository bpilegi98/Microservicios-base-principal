package com.ms.principal;

import com.ms.principal.model.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(Message.class)
public class PrincipalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrincipalApplication.class, args);
    }

}
