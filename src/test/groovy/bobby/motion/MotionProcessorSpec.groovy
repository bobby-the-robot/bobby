package bobby.motion

import bobby.motion.impl.MotionProcessorImpl
import spock.lang.Specification
import spock.lang.Subject

class MotionProcessorSpec extends Specification {

    WheelController wheelController = Mock WheelController

    @Subject
    MotionProcessor motionProcessor = new MotionProcessorImpl(wheelController)

    def "test motion forward"() {
        given:
        Step forward = new Step(Speed.AVERAGE, Direction.FORWARD)

        when:
        motionProcessor.move(forward)

        then:
        1 * wheelController.moveForward()
        0 * _
    }

    def "test motion backward"() {
        given:
        Step backward = new Step(Speed.AVERAGE, Direction.BACK)

        when:
        motionProcessor.move(backward)

        then:
        1 * wheelController.moveBackward()
        0 * _
    }

    def "test motion right"() {
        given:
        Step right = new Step(Speed.AVERAGE, Direction.RIGHT)

        when:
        motionProcessor.move(right)

        then:
        1 * wheelController.turnRight()
        0 * _
    }

    def "test motion left"() {
        given:
        Step left = new Step(Speed.AVERAGE, Direction.LEFT)

        when:
        motionProcessor.move(left)

        then:
        1 * wheelController.turnLeft()
        0 * _
    }

    def "test stop"() {
        given:
        Step stop = new Step(Speed.AVERAGE, Direction.STOP)

        when:
        motionProcessor.move(stop)

        then:
        1 * wheelController.stop()
        0 * _
    }

    def "test pulse forward"() {
        given:
        Step forward = new Step(Speed.AVERAGE, Direction.FORWARD)

        when:
        motionProcessor.pulse(forward)

        then:
        1 * wheelController.pulseForward()
        0 * _
    }

    def "test pulse backward"() {
        given:
        Step backward = new Step(Speed.AVERAGE, Direction.BACK)

        when:
        motionProcessor.pulse(backward)

        then:
        1 * wheelController.pulseBackward()
        0 * _
    }

    def "test pulse right"() {
        given:
        Step right = new Step(Speed.AVERAGE, Direction.RIGHT)

        when:
        motionProcessor.pulse(right)

        then:
        1 * wheelController.pulseRight()
        0 * _
    }

    def "test pulse left"() {
        given:
        Step left = new Step(Speed.AVERAGE, Direction.LEFT)

        when:
        motionProcessor.pulse(left)

        then:
        1 * wheelController.pulseLeft()
        0 * _
    }
}
