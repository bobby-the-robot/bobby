package bobby;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import bobby.core.Controller;
import uk.co.caprica.picam.Camera;
import uk.co.caprica.picam.CameraConfiguration;
import uk.co.caprica.picam.FilePictureCaptureHandler;
import uk.co.caprica.picam.enums.Encoding;

import java.io.File;

import static uk.co.caprica.picam.PicamNativeLibrary.installTempLibrary;

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
	@SneakyThrows
	public void run(ApplicationArguments args) {
		camera();
		log.info("Starting the bobby app...");
		controller.init();
	}

	@SneakyThrows
	private void camera() {
		try {
			installTempLibrary();
		} catch(Exception e) {
			System.out.println("Failed to load camera library");
			e.printStackTrace();
		}

		CameraConfiguration config = CameraConfiguration.cameraConfiguration()
				.width(1920)
				.height(1080)
				.encoding(Encoding.JPEG)
				.quality(85);

		try (Camera camera = new Camera(config)) {
			camera.takePicture(new FilePictureCaptureHandler(new File("picam1.jpg")));
			camera.takePicture(new FilePictureCaptureHandler(new File("picam2.jpg")));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
