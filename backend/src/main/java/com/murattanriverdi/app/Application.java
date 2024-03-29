package com.murattanriverdi.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // proje ayağa kalktığında default olarak user eklemesi için.
/*
    @Bean
    @Profile("dev")
    CommandLineRunner userCreator(UserRepository userRepository) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userRepository.deleteAll();
        return (args) -> {
            for (var i = 1; i <= 25; i++) {
                User user = new User();
                user.setUsername("user" + i);
                user.setEmail("user" + i + "@emaill.com");
                user.setPassword(passwordEncoder.encode("Aa.12345"));
                user.setActive(true);
                userRepository.save(user);
            }
        };
    }*/

}
