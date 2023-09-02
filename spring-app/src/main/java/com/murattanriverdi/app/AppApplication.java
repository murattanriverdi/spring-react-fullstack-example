package com.murattanriverdi.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}


	//uygulama ayağa kalkarken kullanıcı oluşturma.
	/*@Bean
	CommandLineRunner createUser(IUserService userService){
		return args -> {
			UserDto user = new UserDto();
			user.setUsername("user1");
			user.setName("user");
			user.setPassword("test");

			userService.save(user);
		};
	}*/

}
