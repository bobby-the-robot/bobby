package bobby.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import bobby.core.Controller;
import bobby.core.Output;
import bobby.motion.Wheel;
import bobby.motion.impl.WheelImpl;

import static bobby.configuration.WheelConfigurationProperties.Side;

@Configuration
@RequiredArgsConstructor
public class WheelControllerConfiguration {

    private final WheelConfigurationProperties wheelConfiguration;
    private final Controller controller;

    @Bean
    public Wheel rightWheel() {
        Side right = wheelConfiguration.getRight();
        Output rightForwardOutput = controller.initOutput(right.getForward());
        Output rightBackwardOutput = controller.initOutput(right.getBack());
        return new WheelImpl(rightForwardOutput, rightBackwardOutput);
    }

    @Bean
    public Wheel leftWheel() {
        Side left = wheelConfiguration.getLeft();
        Output leftForwardOutput = controller.initOutput(left.getForward());
        Output leftBackwardOutput = controller.initOutput(left.getBack());
        return new WheelImpl(leftForwardOutput, leftBackwardOutput);
    }
}
