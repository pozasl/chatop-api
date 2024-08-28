package com.chatop.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.chatop.api.controller.AuthController;
import com.chatop.api.controller.MessageController;
import com.chatop.api.controller.RentalController;
import com.chatop.api.controller.UserController;

@SpringBootTest
class ApiApplicationTests {

	@Autowired
	private AuthController authController;

	@Autowired
	private UserController userController;

	@Autowired
	private RentalController rentalController;

	@Autowired
	private MessageController messageController;


	@Test
	void contextLoads() {
		assertNotNull(authController);
		assertNotNull(userController);
		assertNotNull(rentalController);
		assertNotNull(messageController);
	}

}
