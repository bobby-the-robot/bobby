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
        wheelController.forward()

        then:
        1 * rightWheel.forward()
        1 * leftWheel.forward()
        0 * _
    }

    def "test right()"() {
        when:
        wheelController.right()

        then:
        1 * rightWheel.forward()
        1 * leftWheel.backward()
        0 * _
    }

    def "test left()"() {
        when:
        wheelController.left()

        then:
        1 * rightWheel.backward()
        1 * leftWheel.forward()
        0 * _
    }

    def "test backward()"() {
        when:
        wheelController.backward()

        then:
        1 * rightWheel.backward()
        1 * leftWheel.backward()
        0 * _
    }
}
