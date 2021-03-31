package com.ms.principal.controller;

import com.ms.principal.service.ApiCallerService;
import com.ms.principal.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api2")
public class MessageController {

    @Autowired
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    //Devuelve el response del microservicio base concatenado con el nombre del usuario.

    //Devuelve "Welcome username!" pasando el dato por param
    @GetMapping("/welcome")
    public String getWelcomeMessage(@RequestParam String username)
    {
        return messageService.getWelcomeMessageWithUsernameGet(username);
    }

    //Devuelve "Welcome username!" pasando el dato por body
    @PostMapping("/welcome")
    public String getWelcomeMessageWithUsername(@RequestBody String username)
    {
        return messageService.getWelcomeMessageWithUsernamePost(username);
    }

}
