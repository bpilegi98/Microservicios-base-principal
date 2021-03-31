package com.ms.principal.service

import com.ms.principal.service.ApiCallerService;
import com.ms.principal.service.MessageService;
import spock.lang.Specification;

class MessageServiceTest extends Specification {

    def apiCallerService = Mock(ApiCallerService)

    def messageService = new MessageService(apiCallerService)


    def "getWelcomeMessageWithUsernameGet"() {
        given:
        def expectedResponse = "Welcome Bianca!"
        apiCallerService.getWelcomeMessageWithUsernameGet() >> "Welcome"
        when:
        def response = messageService.getWelcomeMessageWithUsernameGet("Bianca")
        then:
        response.equals(expectedResponse)
    }

    def "getWelcomeMessageWithUsernamePost"()
    {
        given:
        def expectedResponse = "Welcome Bianca!"
        apiCallerService.getWelcomeMessageWithUsernamePost("Bianca") >> expectedResponse
        when:
        def response = messageService.getWelcomeMessageWithUsernamePost("Bianca")
        then:
        response.equals(expectedResponse)
    }
}