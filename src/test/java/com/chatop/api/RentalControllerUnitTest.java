package com.chatop.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.InputStream;
import java.security.Principal;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import com.chatop.api.controller.RentalController;
import com.chatop.api.service.FileStorageServiceImpl;
import com.chatop.api.service.RentalServiceImpl;

@WebMvcTest(controllers = RentalController.class)
@AutoConfigureMockMvc(addFilters = false)
public class RentalControllerUnitTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private RentalServiceImpl rentalService;

  @MockBean
  private FileStorageServiceImpl fileStorageService;

  @Test
  public void getRentals() throws Exception {
    mockMvc.perform(get("/api/rentals")).andExpect(status().isOk());
  }

  @Test
    void createRental() throws Exception {
        Principal mockPrincipal = Mockito.mock(Authentication.class);
        Mockito.when(mockPrincipal.getName()).thenReturn("bob@test.com");
        InputStream jpgStream = this.getClass().getClassLoader().getResourceAsStream("img/test.jpg");
        MockMultipartFile file = new MockMultipartFile(
            "picture", 
            "test.jpg", 
            MediaType.IMAGE_JPEG_VALUE,
            jpgStream
        );

        mockMvc.perform(multipart("/api/rentals")
            .file(file)
            .param("name", "test")
            .param("surface", "50")
            .param("price", "200")
            .param("description", "A description test...")
            .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
            .principal(mockPrincipal)
        ).andExpect(status().isOk());
    }
    
    @Test
    void getRentalById() throws Exception {
        mockMvc.perform(get("/api/rentals/8")).andExpect(status().isOk());
    }

    @Test
    void updateRentalById() throws Exception {
        Principal mockPrincipal = Mockito.mock(Authentication.class);
        Mockito.when(mockPrincipal.getName()).thenReturn("alice@test.com");

        mockMvc.perform(put("/api/rentals/8")
            .param("name", "test PUT")
            .param("surface", "55")
            .param("price", "220")
            .param("description", "An edited description test...")
            .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
            .principal(mockPrincipal)
        ).andExpect(status().isOk());
    }
    
}
