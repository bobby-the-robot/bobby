package bobby.configuration;

import bobby.core.Controller;
import bobby.motion.MotionProcessor;
import bobby.sensor.ListenerAction;
import bobby.sensor.distance.DistanceListenerAction;
import bobby.sensor.motion.MotionListenerAction;
import bobby.sensor.sound.SoundListenerAction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SensorControllerConfiguration {

    private final Controller controller;

    @Bean
    public ListenerAction distanceListenerAction(@Value("${sensor.distance.pin}") int pin,
                                                 MotionProcessor motionProcessor) {
        ListenerAction action = new DistanceListenerAction(motionProcessor);
        controller.initInput(pin, action);
        return action;
    }

    @Bean
    public ListenerAction motionListenerAction(@Value("${sensor.motion.pin}") int pin,
                                               @Value("${sensor.motion.interval}") int eventInterval,
                                               MotionProcessor motionProcessor) {
        ListenerAction action = new MotionListenerAction(eventInterval, motionProcessor);
        controller.initInput(pin, action);
        return action;
    }

    @Bean
    public ListenerAction soundListenerAction(@Value("${sensor.sound.pin}") int pin,
                                              @Value("${sensor.sound.interval}") int eventInterval,
                                              MotionProcessor motionProcessor) {
        ListenerAction action = new SoundListenerAction(eventInterval, motionProcessor);
        controller.initInput(pin, action);
        return action;
    }
}
