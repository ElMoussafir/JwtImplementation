package com.example.jwtimplementation;

import com.example.jwtimplementation.api.config.PasswordConfig;
import com.example.jwtimplementation.api.entities.User;
import com.example.jwtimplementation.api.repository.UserRepository;
import com.example.jwtimplementation.api.service.EmailService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import static com.example.jwtimplementation.api.config.ApplicationUserRole.*;

@SpringBootApplication
public class JwtimplementationApplication {
	private UserRepository userRepository;
	private PasswordConfig passwordConfig;
	private EmailService emailService;

	public JwtimplementationApplication(UserRepository userRepository, PasswordConfig passwordConfig, EmailService emailService) {
		this.userRepository = userRepository;
		this.passwordConfig = passwordConfig;
		this.emailService = emailService;
	}

	@PostConstruct
	public void initUsers() {
		String password = passwordConfig.passwordEncoder().encode("password");
		List<User> users = Stream.of(
			new User(1,"Omar",password, "omar_lam@hotmail.com", ADMIN),
			new User(2,"Houdeyfa", password , "houdeyfa@hotmail.com", ADMIN),
			new User(3,"Assiya", password , "assiya@hotmail.com", USER_ROLE),
			new User(4,"Hajar", password , "hajar@hotmail.com", USER_ROLE),
			new User(5,"Mohamed", password , "Mohamed@hotmail.com", USER_ROLE),
			new User(6,"Ibrahim", password , "ibrahim@hotmail.com", ADMIN))
			.collect(Collectors.toList());

		this.userRepository.saveAll(users);
	}
	public static void main(String[] args) {
		SpringApplication.run(JwtimplementationApplication.class, args);
	}


}
