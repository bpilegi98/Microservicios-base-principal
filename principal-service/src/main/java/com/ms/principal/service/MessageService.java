package com.ms.principal.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;

@Service
@PropertySources({@PropertySource("classpath:application.yml")})
public class MessageService {

    private final ApiCallerService apiCallerService;

    private final RedisService redisService;

    //Recordatorio: No se puede usar la anotacion Value en un static
    @Value("${message.prefix}")
    private String PREFIX;

    public MessageService(ApiCallerService apiCallerService, RedisService redisService) {
        this.apiCallerService = apiCallerService;
        this.redisService = redisService;
    }

    public String getWelcomeMessageWithUsernameGet(String username)
    {
        this.registerTimesServiceCalled();
        return this.processMessageGet(username);
    }

    public String getWelcomeMessageWithUsernamePost(String username)
    {
        this.registerTimesServiceCalled();
        return this.processMessagePost(username);
    }

    //Aumenta en una unidad el registro de las veces que se llamó
    //el servicio. En caso de que este el valor en nulo, se lo inicializa en 0 para
    //para que pueda iniciar bien el conteo
    public void registerTimesServiceCalled()
    {
        if(redisService.getTimesCalled() == null)
            redisService.save(0);
        redisService.save(redisService.getTimesCalled() + 1);
    }

    //Resetea el contador de veces llamado a 0
    public void resetTimesServiceCalled()
    {
        redisService.save(0);
    }

    //METODO GET: Recibe el usuario pasado por parámetro y devuelve un mensaje distinto
    //dependiendo la cantidad de veces que se llamó al servicio
    public String processMessageGet(String username)
    {
        if(redisService.getTimesCalled() < 5)
            return PREFIX + apiCallerService.getWelcomeMessageWithUsernameGet() + " " + username + "!";
        else
            if(redisService.getTimesCalled() < 10)
                return PREFIX + apiCallerService.getWelcomeMessageWithUsernameGet() + " " + username + "! You're back!";
            else
                return PREFIX + apiCallerService.getWelcomeMessageWithUsernameGet() + " " + username + "! You're back! We missed you!";
    }

    //METODO POST: Recibe el usuario pasado por parámetro y devuelve un mensaje distinto
    //dependiendo la cantidad de veces que se llamó al servicio
    public String processMessagePost(String username)
    {
        if(redisService.getTimesCalled() < 5)
            return PREFIX + apiCallerService.getWelcomeMessageWithUsernamePost(username);
        else
        if(redisService.getTimesCalled() < 10)
            return PREFIX + apiCallerService.getWelcomeMessageWithUsernamePost(username) + " You're back!";
        else
            return PREFIX + apiCallerService.getWelcomeMessageWithUsernamePost(username) + " You're back! We missed you!";
    }
}