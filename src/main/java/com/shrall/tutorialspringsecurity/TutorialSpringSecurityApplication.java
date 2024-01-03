package com.shrall.tutorialspringsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.shrall.tutorialspringsecurity.entities.Role;
import com.shrall.tutorialspringsecurity.entities.User;
import com.shrall.tutorialspringsecurity.repositories.UserRepository;

@SpringBootApplication
public class TutorialSpringSecurityApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(TutorialSpringSecurityApplication.class, args);
	}

	public void run(String... args) {
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		if (adminAccount == null) {
			User admin = new User();
			admin.setFirstName("Admin");
			admin.setLastName("Admin");
			admin.setEmail("admin@gmail.com");
			admin.setPassword(new BCryptPasswordEncoder().encode("wars1234"));
			admin.setRole(Role.ADMIN);
			userRepository.save(admin);
		}
	}

}
