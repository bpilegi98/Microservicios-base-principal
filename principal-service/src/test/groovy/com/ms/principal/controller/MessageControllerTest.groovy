package com.ms.principal.controller

import com.ms.principal.service.ApiCallerService
import com.ms.principal.service.MessageService
import org.junit.jupiter.api.BeforeAll
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.util.ReflectionTestUtils
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import org.springframework.test.web.servlet.MockMvc
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
class MessageControllerTest extends Specification {

    def apiCallerService = Mock(ApiCallerService)

    def messageService = new MessageService(apiCallerService, redisService)

    def messageController = new MessageController(messageService)

    private MockMvc mockMvc

    @BeforeAll
    void setup()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(messageController).build()
        ReflectionTestUtils.setField(messageService, "apiCallerService", apiCallerService)
        ReflectionTestUtils.setField(messageService, "PREFIX", "You are talking to Principal: ")
    }

    //No puedo hacer que el prefix levante el valor :c
    //No se como mockear ese comportamiento -_-

    def "GetWelcomeMessage"() {
        given:
        def messageExpected = "You are talking to Principal: Welcome Bianca!"
        apiCallerService.welcomeMessageWithUsernameGet >> "Welcome"
        messageService.getWelcomeMessageWithUsernameGet("Bianca") >> messageExpected
        expect: "Status is 200 OK with matching message"
        mockMvc.perform(get("/api2/welcome")
                .param("username", "Bianca"))
                .andExpect(status().is(200))
                .andExpect(content().string(messageExpected))
    }

    def "GetWelcomeMessageWithUsername"() {
        given:
        def messageExpected = "You are talking to Principal: Welcome Bianca!"
        apiCallerService.getWelcomeMessageWithUsernamePost("Bianca") >> "Welcome Bianca!"
        messageService.getWelcomeMessageWithUsernamePost("Bianca") >> messageExpected
        expect: "Status is 200 OK with matching message"
        mockMvc.perform(post("/api2/welcome")
                .content("Bianca"))
                .andExpect(status().is(200))
                .andExpect(content().string(messageExpected))
    }
}
