package com.chatop.api;

import org.springframework.boot.SpringApplication;

/**
 * Chatop API test main.
 */
public class TestApiApplication {

  public static void main(String[] args) {
    SpringApplication.from(ApiApplication::main).run(args);
  }
}