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
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import static bobby.configuration.Constants.STOMP_CONTROLLER_URL;

@Configuration
public class Config {

    private final Controller controller;
    private final WheelConfiguration wheelConfiguration;
    private final SensorConfiguration sensorConfiguration;
    private final MotionProcessor motionProcessor;
    private final StompSessionHandlerAdapter stompSessionHandler;

    public Config(Controller controller) {
        this.controller = controller;
        this.wheelConfiguration = new WheelConfiguration();
        this.sensorConfiguration = new SensorConfiguration();
        this.motionProcessor = new MotionProcessorImpl(initWheelController());
        this.stompSessionHandler = new WebSocketStompSessionHandlerImpl(motionProcessor);

        initDistanceListenerAction();
        initMotionListenerAction();
        initSoundListenerAction();
        configureStompClient();
    }

    @Bean
    public GpioController gpioController() {
        return GpioFactory.getInstance();
    }

    private ListenerAction initDistanceListenerAction() {
        ListenerAction action = new DistanceListenerAction(motionProcessor);
        controller.initInput(sensorConfiguration.getDistance().getPin(), action);
        return action;
    }

    private ListenerAction initMotionListenerAction() {
        ListenerAction action = new MotionListenerAction(sensorConfiguration.getMotion().getInterval(), motionProcessor);
        controller.initInput(sensorConfiguration.getMotion().getPin(), action);
        return action;
    }

    private ListenerAction initSoundListenerAction() {
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

    private void configureStompClient() {
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        stompClient.connect(STOMP_CONTROLLER_URL, stompSessionHandler);
    }
}
