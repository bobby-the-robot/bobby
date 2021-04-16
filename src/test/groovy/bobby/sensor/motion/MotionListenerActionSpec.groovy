package bobby.sensor.motion

import bobby.motion.Direction
import bobby.motion.MotionProcessor
import bobby.motion.Speed
import bobby.motion.Step
import bobby.sensor.ListenerAction
import spock.lang.Specification
import spock.lang.Subject

class MotionListenerActionSpec extends Specification {

    MotionProcessor motionProcessor = Mock MotionProcessor

    @Subject
    ListenerAction listenerAction = new MotionListenerAction(1000, motionProcessor)

    def "test run()"() {
        given:
        Step forward = new Step(Speed.AVERAGE, Direction.FORWARD)

        when:
        listenerAction.run()

        then:
        1 * motionProcessor.pulse(forward)
        0 * _
    }

    def "test run() for 2 sequential events more frequent than allowed interval"() {
        given: 'event interval is 1000 ms'
        Step forward = new Step(Speed.AVERAGE, Direction.FORWARD)

        when:
        listenerAction.run()
        listenerAction.run()

        then: 'only 1 sequence is added'
        1 * motionProcessor.pulse(forward)
        0 * _
    }

    def "test registerEvent() for 2 sequential events matching interval"() {
        given: 'event interval is 0'
        ListenerAction listenerAction = new MotionListenerAction(0, motionProcessor)
        Step forward = new Step(Speed.AVERAGE, Direction.FORWARD)

        when:
        listenerAction.run()
        listenerAction.run()

        then: '2 sequences are added'
        2 * motionProcessor.pulse(forward)
        0 * _
    }
}
