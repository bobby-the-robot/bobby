package bobby.core.raspberrypi.impl;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import bobby.core.Output;

@RequiredArgsConstructor
public class RaspberryPiOutputImpl implements Output {

    private final long interval;
    private final GpioPinDigitalOutput gpioPinDigitalOutput;

    @Override
    @SneakyThrows
    public void pulse() {
        gpioPinDigitalOutput.pulse(interval);
    }

    @Override
    public void high() {
        gpioPinDigitalOutput.high();
    }

    @Override
    public void low() {
        gpioPinDigitalOutput.low();
    }
}
