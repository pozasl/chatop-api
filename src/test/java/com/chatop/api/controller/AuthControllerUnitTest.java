package com.chatop.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;

import com.chatop.api.service.JwtServiceImpl;
import com.chatop.api.service.UserServiceImpl;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerUnitTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserServiceImpl userService;

  @MockBean
  private JwtServiceImpl jwtService;

  @MockBean
  private AuthenticationManager authenticationManager;


  @Test
  public void testRegister() throws Exception {
    String jsonStr = "{\"email\": \"alice@test.com\",\"name\":\"alice\",\"password\": \"pass1234\"}";
    mockMvc.perform(
      post("/api/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonStr))
      .andExpect(status().isOk());
  }

  @Test
  public void testRegisterShortPassRejection() throws Exception {
    String jsonStr = "{\"email\": \"jeff@test.com\",\"name\":\"jeff\",\"password\": \"pass\"}";
    mockMvc.perform(
      post("/api/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonStr))
      .andExpect(status().isBadRequest());
  }

  @Test
  public void testRegisterWrongEmailRejection() throws Exception {
    String jsonStr = "{\"email\": \"jeff-test.com\",\"name\":\"jeff\",\"password\": \"pass1234\"}";
    mockMvc.perform(
      post("/api/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonStr))
      .andExpect(status().isBadRequest());
  }

  @Test
  public void testRegisterEmptyNameRejection() throws Exception {
    String jsonStr = "{\"email\": \"jeff@test.com\",\"name\":\"\",\"password\": \"pass1234\"}";
    mockMvc.perform(
      post("/api/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonStr))
      .andExpect(status().isBadRequest());
  }

  @Test
  public void testLogin() throws Exception {
    String jsonStr = "{\"email\": \"alice@test.com\",\"password\": \"pass1234\"}";
    mockMvc.perform(
      post("/api/auth/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonStr))
      .andExpect(status().isOk());
  }

  @Test
  public void testLoginWithoutEmailRejection() throws Exception {
    String jsonStr = "{\"email\": \"\",\"password\": \"pass1234\"}";
    mockMvc.perform(
      post("/api/auth/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonStr))
      .andExpect(status().isBadRequest());
  }

  @Test
  public void testLoginWithoutPasswordRejection() throws Exception {
    String jsonStr = "{\"email\": \"\",\"password\": \"\"}";
    mockMvc.perform(
      post("/api/auth/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonStr))
      .andExpect(status().isBadRequest());
  }
    
}
