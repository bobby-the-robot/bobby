package bobby;

import bobby.core.raspberrypi.impl.RaspberryBobby;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class BobbyApplication {

	public static void main(String[] args) {
		log.info("Starting the bobby app...");
		new RaspberryBobby().init();
	}
}
