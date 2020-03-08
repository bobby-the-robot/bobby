package bobby.core

import com.pi4j.io.gpio.GpioController
import com.pi4j.io.gpio.PinState
import com.pi4j.io.gpio.RaspiPin
import bobby.core.raspberrypi.impl.RaspberryPiOutputFactoryImpl
import bobby.core.raspberrypi.impl.RaspberryPiOutputImpl
import spock.lang.Specification
import spock.lang.Subject

class OutputFactorySpec extends Specification {

    long interval = 0
    GpioController gpioController = Mock GpioController

    @Subject
    OutputFactory outputFactory = new RaspberryPiOutputFactoryImpl(interval, gpioController)

    def "test getInstance()"() {
        given:

        when:
        Output output = outputFactory.getInstance(1)

        then:
        1 * gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_01, PinState.LOW)
        0 * _

        and:
        output instanceof RaspberryPiOutputImpl
    }
}
