package bobby.configuration;

import bobby.motion.MotionProcessor;
import bobby.motion.WheelController;
import bobby.motion.impl.MotionProcessorImpl;
import bobby.motion.impl.WheelControllerImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import bobby.core.Controller;
import bobby.core.Output;
import bobby.motion.Wheel;
import bobby.motion.impl.WheelImpl;

import static bobby.configuration.WheelConfiguration.Side;

@Configuration
@RequiredArgsConstructor
public class WheelControllerConfiguration {

    private final WheelConfiguration wheelConfiguration = new WheelConfiguration();
    private final Controller controller;

    @Bean
    public MotionProcessor motionProcessor() {
        return new MotionProcessorImpl(initWheelController());
    }

    private Wheel initRightWheel() {
        Side right = wheelConfiguration.getRight();
        Output forwardOutput = controller.initOutput(right.getForward());
        Output backwardOutput = controller.initOutput(right.getBack());
        return new WheelImpl(forwardOutput, backwardOutput);
    }

    private Wheel initLeftWheel() {
        Side left = wheelConfiguration.getLeft();
        Output forwardOutput = controller.initOutput(left.getForward());
        Output backwardOutput = controller.initOutput(left.getBack());
        return new WheelImpl(forwardOutput, backwardOutput);
    }

    private WheelController initWheelController() {
        Wheel rightWheel = initRightWheel();
        Wheel leftWheel = initLeftWheel();
        return new WheelControllerImpl(rightWheel, leftWheel);
    }
}
