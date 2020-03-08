package bobby.core

import com.pi4j.io.gpio.GpioController
import com.pi4j.io.gpio.PinPullResistance
import com.pi4j.io.gpio.RaspiPin
import bobby.core.raspberrypi.impl.RaspberryPiInputFactoryImpl
import bobby.core.raspberrypi.impl.RaspberryPiInputImpl
import spock.lang.Specification
import spock.lang.Subject

class InputFactorySpec extends Specification {

    GpioController gpioController = Mock GpioController

    @Subject
    InputFactory inputFactory = new RaspberryPiInputFactoryImpl(gpioController)

    def "test getInstance()"() {
        given:
        int pinNumber = 1

        when:
        def result = inputFactory.getInstance(pinNumber)

        then:
        1 * gpioController.provisionDigitalInputPin(RaspiPin.GPIO_01, PinPullResistance.PULL_DOWN)
        0 * _

        and:
        result instanceof RaspberryPiInputImpl
    }
}
