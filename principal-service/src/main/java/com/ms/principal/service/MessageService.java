package com.ms.principal.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;

@Service
@PropertySources({@PropertySource("classpath:application.yml")})
public class MessageService {

    private final ApiCallerService apiCallerService;

    //Recordatorio: No se puede usar la anotacion Value en un static
    @Value("${message.prefix}")
    private String PREFIX;

    public MessageService(ApiCallerService apiCallerService) {
        this.apiCallerService = apiCallerService;
    }

    public String getWelcomeMessageWithUsernameGet(String username)
    {
        return PREFIX + apiCallerService.getWelcomeMessageWithUsernameGet() + " " + username + "!";
    }

    public String getWelcomeMessageWithUsernamePost(String username)
    {
        return PREFIX + apiCallerService.getWelcomeMessageWithUsernamePost(username);
    }

}