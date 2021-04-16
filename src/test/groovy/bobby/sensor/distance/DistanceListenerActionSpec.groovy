package bobby.sensor.distance

import bobby.motion.Direction
import bobby.motion.MotionProcessor
import bobby.motion.Speed
import bobby.motion.Step
import bobby.sensor.distance.impl.DistanceListenerActionImpl
import spock.lang.Specification
import spock.lang.Subject

class DistanceListenerActionSpec extends Specification {

    MotionProcessor motionProcessor = Mock MotionProcessor

    @Subject
    DistanceListenerAction distanceListenerAction = new DistanceListenerActionImpl(motionProcessor)

    def "test run()"() {
        given:
        Step right = new Step(Speed.FAST, Direction.RIGHT)

        when:
        distanceListenerAction.run()

        then:
        1 * motionProcessor.pulse(right)
        0 * _
    }
}
