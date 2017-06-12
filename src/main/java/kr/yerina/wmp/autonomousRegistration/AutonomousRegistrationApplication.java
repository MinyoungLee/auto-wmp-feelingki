package kr.yerina.wmp.autonomousRegistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
public class AutonomousRegistrationApplication {
	public static void main(String[] args) {
		SpringApplication.run(AutonomousRegistrationApplication.class, args);
	}
}
