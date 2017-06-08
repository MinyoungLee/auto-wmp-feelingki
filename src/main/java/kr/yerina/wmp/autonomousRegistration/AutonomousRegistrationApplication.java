package kr.yerina.wmp.autonomousRegistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AutonomousRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutonomousRegistrationApplication.class, args);
	}
}
