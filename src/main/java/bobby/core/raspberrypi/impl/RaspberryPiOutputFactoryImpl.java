package bobby.core.raspberrypi.impl;

import com.pi4j.io.gpio.*;
import lombok.RequiredArgsConstructor;
import bobby.core.Output;
import bobby.core.OutputFactory;
import bobby.core.raspberrypi.RaspberryPiPinMapping;

import static bobby.configuration.Constants.OPERATIONS_INTERVAL;

@RequiredArgsConstructor
public class RaspberryPiOutputFactoryImpl implements OutputFactory {

    private static final PinState STATE = PinState.LOW;

    private final GpioController gpioController;

    @Override
    public Output getInstance(int pinNumber) {
        Pin pin = RaspberryPiPinMapping.PIN_MAPPING.get(pinNumber);
        GpioPinDigitalOutput gpioOutput = gpioController.provisionDigitalOutputPin(pin, STATE);
        return new RaspberryPiOutputImpl(OPERATIONS_INTERVAL, gpioOutput);
    }
}
