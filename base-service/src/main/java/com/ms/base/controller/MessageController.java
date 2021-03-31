package com.ms.base.controller;

import com.ms.base.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api1")
public class MessageController {

    @Autowired
    private MessageService messageService;

    //Devuelve “Welcome!”
    @GetMapping("/welcome")
    public String getWelcomeMessage()
    {
        return messageService.getWelcomeMessage();
    }

    //Devuelve “Welcome”
    @GetMapping("/welcome/")
    public String getWelcomeMessageNoSpecialChar()
    {
        return messageService.getWelcomeMessageNoSpecialChar();
    }

    //Crear un post en el base donde se le envia el username por el body y
    // este lo devuelve concatenado. Consumirlo desde el principal
    @PostMapping("/welcome")
    public String getMessageWithUsername(@RequestBody String username)
    {
        return messageService.getWelcomeMessageWithUsername(username);
    }
}
