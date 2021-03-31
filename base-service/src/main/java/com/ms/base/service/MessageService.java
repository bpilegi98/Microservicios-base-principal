package com.ms.base.service;

import org.springframework.stereotype.Service;


@Service
public class MessageService {

    static String COMPLETE_WELCOME = "Welcome!";
    static String WELCOME = "Welcome";


    public String getWelcomeMessage()
    {
       return COMPLETE_WELCOME;
    }

    public String getWelcomeMessageNoSpecialChar()
    {
        return WELCOME;
    }

    public String getWelcomeMessageWithUsername(String username)
    {
        return getWelcomeMessageNoSpecialChar() + " " + username + "!";
    }

}
