package com.chatop.api.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.security.Principal;

import com.chatop.api.service.MessageService;

@WebMvcTest(controllers = MessageController.class)
@AutoConfigureMockMvc(addFilters = false)
class MessageControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MessageService messageService;

    @Test
    void createMessage() throws Exception {
        String msg = "{\"message\": \"Hello worl!\", \"user_id\": 1, \"rental_id\":1}";
        Principal mockPrincipal = Mockito.mock(Authentication.class);
        Mockito.when(mockPrincipal.getName()).thenReturn("bob@test.com");

        mockMvc.perform(post("/api/messages")
            .content(msg)
            .contentType(MediaType.APPLICATION_JSON)
            .principal(mockPrincipal)
        ).andExpect(status().isOk());
    }
    
}
