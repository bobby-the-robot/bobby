package bobby.core

import com.pi4j.io.gpio.GpioPinDigitalOutput
import bobby.core.raspberrypi.impl.RaspberryPiOutputImpl
import spock.lang.Specification
import spock.lang.Subject

class OutputSpec extends Specification {

    long interval = 0
    GpioPinDigitalOutput gpioPinDigitalOutput = Mock GpioPinDigitalOutput

    @Subject
    Output output = new RaspberryPiOutputImpl(interval, gpioPinDigitalOutput)

    def "test pulse()" () {
        when:
        output.pulse()

        then:
        1 * gpioPinDigitalOutput.pulse(interval)
        0 * _
    }
}
