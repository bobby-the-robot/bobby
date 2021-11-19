package bobby.core.raspberrypi.impl;

import com.pi4j.io.gpio.*;
import lombok.RequiredArgsConstructor;
import bobby.core.Input;
import bobby.core.InputFactory;
import bobby.core.raspberrypi.RaspberryPiPinMapping;

@RequiredArgsConstructor
public class RaspberryPiInputFactoryImpl implements InputFactory {

    private static final PinPullResistance RESISTANCE = PinPullResistance.PULL_DOWN;

    private final GpioController gpioController;

    @Override
    public Input getInstance(int pinNumber) {
        Pin pin = RaspberryPiPinMapping.PIN_MAPPING.get(pinNumber);
        GpioPinDigitalInput gpioInput = gpioController.provisionDigitalInputPin(pin, RESISTANCE);
        return new RaspberryPiInputImpl(gpioInput);
    }
}
