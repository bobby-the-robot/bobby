package bobby.configuration;

import bobby.core.Controller;
import bobby.core.Output;
import bobby.motion.MotionProcessor;
import bobby.motion.Wheel;
import bobby.motion.WheelController;
import bobby.motion.impl.MotionProcessorImpl;
import bobby.motion.impl.WheelControllerImpl;
import bobby.motion.impl.WheelImpl;
import bobby.remote.impl.WebSocketStompSessionHandlerImpl;
import bobby.sensor.ListenerAction;
import bobby.sensor.distance.DistanceListenerAction;
import bobby.sensor.motion.MotionListenerAction;
import bobby.sensor.sound.SoundListenerAction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

@Configuration
public class Config {

    private final Controller controller;
    private final WheelConfiguration wheelConfiguration;
    private final SensorConfiguration sensorConfiguration;
    private final MotionProcessor motionProcessor;

    public Config(Controller controller) {
        this.controller = controller;
        this.wheelConfiguration = new WheelConfiguration();
        this.sensorConfiguration = new SensorConfiguration();
        this.motionProcessor = new MotionProcessorImpl(initWheelController());
    }

    @Bean
    public StompSessionHandlerAdapter stompSessionHandlerAdapter() {
        return new WebSocketStompSessionHandlerImpl(motionProcessor);
    }

    @Bean
    public ListenerAction distanceListenerAction() {
        ListenerAction action = new DistanceListenerAction(motionProcessor);
        controller.initInput(sensorConfiguration.getDistance().getPin(), action);
        return action;
    }

    @Bean
    public ListenerAction motionListenerAction() {
        ListenerAction action = new MotionListenerAction(sensorConfiguration.getMotion().getInterval(), motionProcessor);
        controller.initInput(sensorConfiguration.getMotion().getPin(), action);
        return action;
    }

    @Bean
    public ListenerAction soundListenerAction() {
        ListenerAction action = new SoundListenerAction(sensorConfiguration.getSound().getInterval(), motionProcessor);
        controller.initInput(sensorConfiguration.getSound().getPin(), action);
        return action;
    }

    private Wheel initRightWheel() {
        WheelConfiguration.Side right = wheelConfiguration.getRight();
        Output forwardOutput = controller.initOutput(right.getForward());
        Output backwardOutput = controller.initOutput(right.getBack());
        return new WheelImpl(forwardOutput, backwardOutput);
    }

    private Wheel initLeftWheel() {
        WheelConfiguration.Side left = wheelConfiguration.getLeft();
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
