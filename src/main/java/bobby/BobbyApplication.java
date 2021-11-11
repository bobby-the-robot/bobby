package bobby;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties
public class BobbyApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(BobbyApplication.class, args);
	}

	@Override
	@SneakyThrows
	public void run(ApplicationArguments args) {
		log.info("Starting the bobby app...");
	}
}
