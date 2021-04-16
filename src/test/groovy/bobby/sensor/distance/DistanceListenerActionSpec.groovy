package bobby.sensor.distance

import bobby.motion.Direction
import bobby.motion.MotionProcessor
import bobby.motion.Speed
import bobby.motion.Step
import bobby.sensor.ListenerAction
import spock.lang.Specification
import spock.lang.Subject

class DistanceListenerActionSpec extends Specification {

    MotionProcessor motionProcessor = Mock MotionProcessor

    @Subject
    ListenerAction listenerAction = new DistanceListenerAction(motionProcessor)

    def "test run()"() {
        given:
        Step right = new Step(Speed.FAST, Direction.RIGHT)

        when:
        listenerAction.run()

        then:
        1 * motionProcessor.pulse(right)
        0 * _
    }
}
