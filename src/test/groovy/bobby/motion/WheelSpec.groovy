package bobby.motion

import bobby.core.Output
import bobby.motion.impl.WheelImpl
import spock.lang.Specification
import spock.lang.Subject

class WheelSpec extends Specification {

    Output forwardOutput = Mock Output
    Output backwardOutput = Mock Output

    @Subject
    Wheel wheel = new WheelImpl(forwardOutput, backwardOutput)

    def "test forward()"() {
        when:
        wheel.forwardPulse()

        then:
        1 * forwardOutput.pulse()
        0 * _
    }

    def "test backward()"() {
        when:
        wheel.backwardPulse()

        then:
        1 * backwardOutput.pulse()
        0 * _
    }
}
