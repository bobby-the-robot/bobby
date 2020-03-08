package bobby;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import bobby.core.Controller;

@Slf4j
@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties
public class BobbyApplication implements ApplicationRunner {

	private final Controller controller;

	public static void main(String[] args) {
		SpringApplication.run(BobbyApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		log.info("Starting the bobby app...");
		controller.init();
	}
}
