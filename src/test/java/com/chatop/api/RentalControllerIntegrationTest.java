package com.chatop.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class RentalControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getRentals() throws Exception {
        mockMvc.perform(get("/api/rentals")).andExpect(status().isOk());
    }

    // @Test
    // void getRentalById() throws Exception {
    //     mockMvc.perform(get("/api/rentals/8")).andExpect(status().isOk());
    // }

}
