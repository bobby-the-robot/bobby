package bobby.configuration;

import lombok.Data;

import static bobby.configuration.Constants.RIGHT_BACKWARD_WHEEL_PIN;
import static bobby.configuration.Constants.RIGHT_FORWARD_WHEEL_PIN;
import static bobby.configuration.Constants.LEFT_FORWARD_WHEEL_PIN;
import static bobby.configuration.Constants.LEFT_BACKWARD_WHEEL_PIN;

@Data
public class WheelConfiguration {

    private Side right = new Side(RIGHT_FORWARD_WHEEL_PIN, RIGHT_BACKWARD_WHEEL_PIN);
    private Side left = new Side(LEFT_FORWARD_WHEEL_PIN, LEFT_BACKWARD_WHEEL_PIN);

    @Data
    public static class Side {

        private final int forward;
        private final int back;
    }
}
