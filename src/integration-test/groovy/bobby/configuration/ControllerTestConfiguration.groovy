package bobby.configuration

import com.pi4j.io.gpio.GpioController
import com.pi4j.io.gpio.GpioPinDigitalInput
import com.pi4j.io.gpio.Pin
import com.pi4j.io.gpio.PinPullResistance
import groovy.transform.AutoImplement

class ControllerTestConfiguration {

    GpioController gpioController() {
        new GpioControllerMock()
    }

    @AutoImplement
    class GpioPinDigitalInputMock implements GpioPinDigitalInput {

    }

    @AutoImplement
    class GpioControllerMock implements GpioController {
        GpioPinDigitalInput provisionDigitalInputPin(Pin pin, PinPullResistance resistance) {
            new GpioPinDigitalInputMock()
        }
    }
}
