package com.chatop.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetUserById() throws Exception {
        mockMvc.perform(get("/api/user/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.name", is("bob")))
        .andExpect(jsonPath("$.email", is("bob@test.com")))
        .andExpect(jsonPath("$.created_at", is("2024/08/14")))
        .andExpect(jsonPath("$.updated_at", is("2024/08/14")));
    }

    @Test
    public void testGetUserByIdShould404() throws Exception {
        mockMvc.perform(get("/api/user/999"))
        .andExpect(status().isNotFound());
    }
    
}
