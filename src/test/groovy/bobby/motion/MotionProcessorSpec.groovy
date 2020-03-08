package bobby.motion

import bobby.motion.impl.MotionProcessorImpl
import spock.lang.Specification
import spock.lang.Subject

class MotionProcessorSpec extends Specification {

    Route route = Mock Route
    TrackController trackController = Mock TrackController

    @Subject
    MotionProcessor motionProcessor = new MotionProcessorImpl(route, trackController)

    def "test no motion"() {
        when:
        motionProcessor.process()

        then:
        1 * route.nextSequence() >> []
        0 * _
    }

    def "test motion forward"() {
        given:
        List<Step> steps = [new Step(Speed.AVERAGE, Direction.FORWARD)]

        when:
        motionProcessor.process()

        then:
        1 * route.nextSequence() >> steps
        1 * trackController.forward()
        0 * _
    }

    def "test motion backward"() {
        given:
        List<Step> steps = [new Step(Speed.AVERAGE, Direction.BACK)]

        when:
        motionProcessor.process()

        then:
        1 * route.nextSequence() >> steps
        1 * trackController.backward()
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
        motionProcessor.process()

        then:
        1 * route.nextSequence() >> steps
        1 * trackController.right()
        1 * trackController.forward()
        1 * trackController.left()
        0 * _
    }
}
