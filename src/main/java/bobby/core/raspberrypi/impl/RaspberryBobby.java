package bobby.core.raspberrypi.impl;

import bobby.remote.MessageReceiver;
import bobby.remote.impl.MessageReceiverImpl;
import bobby.configuration.SensorConfiguration;
import bobby.configuration.WheelConfiguration;
import bobby.core.*;
import bobby.motion.MotionProcessor;
import bobby.motion.Wheel;
import bobby.motion.WheelController;
import bobby.motion.impl.MotionProcessorImpl;
import bobby.motion.impl.WheelControllerImpl;
import bobby.motion.impl.WheelImpl;
import bobby.sensor.ListenerAction;
import bobby.sensor.distance.DistanceListenerAction;
import bobby.sensor.motion.MotionListenerAction;
import bobby.sensor.sound.SoundListenerAction;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

public class RaspberryBobby {

    private final Controller controller;
    private final WheelConfiguration wheelConfiguration;
    private final SensorConfiguration sensorConfiguration;
    private final MotionProcessor motionProcessor;

    public RaspberryBobby() {
        this.controller = initController();
        this.wheelConfiguration = new WheelConfiguration();
        this.sensorConfiguration = new SensorConfiguration();
        this.motionProcessor = new MotionProcessorImpl(initWheelController());
    }

    public void init() {
        initDistanceListenerAction();
        initMotionListenerAction();
        initSoundListenerAction();
        configureMessageReceiver();
    }

    private Controller initController() {
        GpioController gpioController = GpioFactory.getInstance();
        OutputFactory outputFactory = new RaspberryPiOutputFactoryImpl(gpioController);
        InputFactory inputFactory = new RaspberryPiInputFactoryImpl(gpioController);
        ListenerFactory listenerFactory = new RaspberryPiListenerFactoryImpl();
        return new RaspberryPiControllerImpl(inputFactory, outputFactory, listenerFactory);
    }

    private void initDistanceListenerAction() {
        ListenerAction action = new DistanceListenerAction(motionProcessor);
        controller.initInput(sensorConfiguration.getDistance().getPin(), action);
    }

    private void initMotionListenerAction() {
        ListenerAction action = new MotionListenerAction(sensorConfiguration.getMotion().getInterval(), motionProcessor);
        controller.initInput(sensorConfiguration.getMotion().getPin(), action);
    }

    private void initSoundListenerAction() {
        ListenerAction action = new SoundListenerAction(sensorConfiguration.getSound().getInterval(), motionProcessor);
        controller.initInput(sensorConfiguration.getSound().getPin(), action);
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

    private void configureMessageReceiver() {
        MessageReceiver messageReceiver = new MessageReceiverImpl(motionProcessor);
        messageReceiver.run();
    }
}
