package org.aming.web.qq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class WebQqApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebQqApplication.class, args);
	}
}
