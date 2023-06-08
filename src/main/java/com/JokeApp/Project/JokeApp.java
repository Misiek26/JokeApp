package com.JokeApp.Project;

import com.JokeApp.Project.model.User;
import com.JokeApp.Project.model.UserRole;
import com.JokeApp.Project.security.config.auth.AuthenticationService;
import com.JokeApp.Project.security.config.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.JokeApp.Project.model.UserRole.*;

@SpringBootApplication
public class JokeApp {
	public static void main(String[] args){
		SpringApplication.run(JokeApp.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest
					.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(ADMIN)
					.build();
			service.register(admin);


			var manager = RegisterRequest.builder()
					.firstname("Manager")
					.lastname("Manager")
					.email("manager@mail.com")
					.password("1234")
					.role(MANAGER)
					.build();
			service.register(manager);
		};
	}
}
