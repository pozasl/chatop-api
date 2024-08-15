package com.chatop.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.chatop.api.controller.AuthController;
import com.chatop.api.service.UserService;

@WebMvcTest(controllers = AuthController.class)
public class AuthControllerUnitTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @Test
  public void testRegister() throws Exception {
    String jsonStr = "{\"email\": \"alice@test.com\",\"name\":\"alice\",\"password\": \"passss\"}";
    mockMvc.perform(
      post("/api/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonStr))
      .andExpect(status().isOk());
  }

  @Test
  public void testRegisterShortPassRejection() throws Exception {
    String jsonStr = "{\"email\": \"alice@test.com\",\"name\":\"alice\",\"password\": \"pass\"}";
    mockMvc.perform(
      post("/api/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonStr))
      .andExpect(status().isBadRequest());
  }

  @Test
  public void testRegisterWrongEmailRejection() throws Exception {
    String jsonStr = "{\"email\": \"alice-test.com\",\"name\":\"alice\",\"password\": \"pass\"}";
    mockMvc.perform(
      post("/api/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonStr))
      .andExpect(status().isBadRequest());
  }

  @Test
  public void testRegisterEmptyNameRejection() throws Exception {
    String jsonStr = "{\"email\": \"alice@test.com\",\"name\":\"alice\",\"password\": \"pass\"}";
    mockMvc.perform(
      post("/api/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonStr))
      .andExpect(status().isBadRequest());
  }
    
}
