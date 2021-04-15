package bobby.motion

import bobby.motion.impl.MotionProcessorImpl
import spock.lang.Specification
import spock.lang.Subject

class MotionProcessorSpec extends Specification {

    Route route = Mock Route
    WheelController wheelController = Mock WheelController

    @Subject
    MotionProcessor motionProcessor = new MotionProcessorImpl(route, wheelController)

    def "test no motion"() {
        when:
        motionProcessor.processQueue()

        then:
        1 * route.nextSequence() >> []
        0 * _
    }

    def "test motion forward"() {
        given:
        List<Step> steps = [new Step(Speed.AVERAGE, Direction.FORWARD)]

        when:
        motionProcessor.processQueue()

        then:
        1 * route.nextSequence() >> steps
        1 * wheelController.forwardPulse()
        0 * _
    }

    def "test motion backward"() {
        given:
        List<Step> steps = [new Step(Speed.AVERAGE, Direction.BACK)]

        when:
        motionProcessor.processQueue()

        then:
        1 * route.nextSequence() >> steps
        1 * wheelController.backwardPulse()
        0 * _
    }

    def "test complex route"() {
        given:
        List<Step> steps = [
                new Step(Speed.AVERAGE, Direction.RIGHT),
                new Step(Speed.AVERAGE, Direction.FORWARD),
                new Step(Speed.AVERAGE, Direction.LEFT),
        ]

        when:
        motionProcessor.processQueue()

        then:
        1 * route.nextSequence() >> steps
        1 * wheelController.rightPulse()
        1 * wheelController.forwardPulse()
        1 * wheelController.leftPulse()
        0 * _
    }
}
