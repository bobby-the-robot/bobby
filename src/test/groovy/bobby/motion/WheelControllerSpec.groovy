package bobby.motion

import bobby.motion.impl.WheelControllerImpl
import spock.lang.Specification
import spock.lang.Subject

class WheelControllerSpec extends Specification {

    Wheel rightWheel = Mock Wheel
    Wheel leftWheel = Mock Wheel

    @Subject
    WheelController wheelController = new WheelControllerImpl(rightWheel, leftWheel)

    def "test forward()"() {
        when:
        wheelController.forwardPulse()

        then:
        1 * rightWheel.forwardPulse()
        1 * leftWheel.forwardPulse()
        0 * _
    }

    def "test right()"() {
        when:
        wheelController.rightPulse()

        then:
        1 * rightWheel.forwardPulse()
        1 * leftWheel.backwardPulse()
        0 * _
    }

    def "test left()"() {
        when:
        wheelController.leftPulse()

        then:
        1 * rightWheel.backwardPulse()
        1 * leftWheel.forwardPulse()
        0 * _
    }

    def "test backward()"() {
        when:
        wheelController.backwardPulse()

        then:
        1 * rightWheel.backwardPulse()
        1 * leftWheel.backwardPulse()
        0 * _
    }
}
