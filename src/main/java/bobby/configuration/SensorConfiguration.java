package bobby.configuration;

import lombok.Data;

import static bobby.configuration.Constants.DISTANCE_SENSOR_PIN;
import static bobby.configuration.Constants.MOTION_SENSOR_PIN;
import static bobby.configuration.Constants.SOUND_SENSOR_PIN;

@Data
public class SensorConfiguration {

    private final Sensor distance = new Sensor(DISTANCE_SENSOR_PIN, 0);
    private final Sensor motion = new Sensor(MOTION_SENSOR_PIN, 1000);
    private final Sensor sound = new Sensor(SOUND_SENSOR_PIN, 1000);

    @Data
    public static class Sensor {

        private final int pin;
        private final int interval;
    }
}
