package bobby.core.raspberrypi.impl;

import com.pi4j.io.gpio.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import bobby.core.Output;
import bobby.core.OutputFactory;
import bobby.core.raspberrypi.RaspberryPiPinMapping;

@Component
public class RaspberryPiOutputFactoryImpl implements OutputFactory {

    private static final PinState STATE = PinState.LOW;

    private final long interval;
    private final GpioController gpioController;

    public RaspberryPiOutputFactoryImpl(@Value("${operations.interval}") long interval, GpioController gpioController) {
        this.interval = interval;
        this.gpioController = gpioController;
    }

    @Override
    public Output getInstance(int pinNumber) {
        Pin pin = RaspberryPiPinMapping.PIN_MAPPING.get(pinNumber);
        GpioPinDigitalOutput gpioOutput = gpioController.provisionDigitalOutputPin(pin, STATE);
        return new RaspberryPiOutputImpl(interval, gpioOutput);
    }
}
