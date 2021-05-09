package com.blacksheep.service;


import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class CustomerDetailsServiceTest {

	@Test
	public void generate_encrypted_password() {
		String password = "anything";
		BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(password);
		System.out.println(encodedPassword);
		assertNotEquals(password, encodedPassword);
//		assertThat(password, is(not(equalTo(encodedPassword));
	}

}
