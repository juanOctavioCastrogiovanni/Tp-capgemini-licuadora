package domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class TpCapgeminiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpCapgeminiApplication.class, args);
	}

}
