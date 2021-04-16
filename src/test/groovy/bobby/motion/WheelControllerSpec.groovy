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
        wheelController.pulseForward()

        then:
        1 * rightWheel.forwardPulse()
        1 * leftWheel.forwardPulse()
        0 * _
    }

    def "test right()"() {
        when:
        wheelController.pulseRight()

        then:
        1 * rightWheel.forwardPulse()
        1 * leftWheel.backwardPulse()
        0 * _
    }

    def "test left()"() {
        when:
        wheelController.pulseLeft()

        then:
        1 * rightWheel.backwardPulse()
        1 * leftWheel.forwardPulse()
        0 * _
    }

    def "test backward()"() {
        when:
        wheelController.pulseBackward()

        then:
        1 * rightWheel.backwardPulse()
        1 * leftWheel.backwardPulse()
        0 * _
    }

    def "test moveForward()" () {
        when:
        wheelController.moveForward()

        then:
        1 * rightWheel.moveForward()
        1 * leftWheel.moveForward()
        0 * _
    }

    def "test moveBackward()" () {
        when:
        wheelController.moveBackward()

        then:
        1 * rightWheel.moveBackward()
        1 * leftWheel.moveBackward()
        0 * _
    }

    def "test turnRight()"() {
        when:
        wheelController.turnRight()

        then:
        1 * rightWheel.moveForward()
        1 * leftWheel.moveBackward()
        0 * _
    }

    def "test turnLeft()"() {
        when:
        wheelController.turnLeft()

        then:
        1 * rightWheel.moveBackward()
        1 * leftWheel.moveForward()
        0 * _
    }

    def "test stop()"() {
        when:
        wheelController.stop()

        then:
        1 * rightWheel.stop()
        1 * leftWheel.stop()
        0 * _
    }
}
