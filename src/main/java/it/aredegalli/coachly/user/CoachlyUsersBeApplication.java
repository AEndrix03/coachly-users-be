package it.aredegalli.coachly.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "it.aredegalli.coachly")
public class CoachlyUsersBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoachlyUsersBeApplication.class, args);
	}

}
