package bobby.sensor.sound

import bobby.motion.Direction
import bobby.motion.MotionProcessor
import bobby.motion.Speed
import bobby.motion.Step
import bobby.sensor.sound.impl.SoundListenerActionImpl
import spock.lang.Specification
import spock.lang.Subject

class SoundListenerActionSpec extends Specification {

    MotionProcessor motionProcessor = Mock MotionProcessor

    @Subject
    SoundListenerAction soundListenerAction = new SoundListenerActionImpl(1000, motionProcessor)

    def "test run()"() {
        given:
        Step backward = new Step(Speed.SLOW, Direction.BACK);

        when:
        soundListenerAction.run()

        then:
        1 * motionProcessor.pulse(backward)
        0 * _
    }

    def "test run() for 2 sequential events more frequent than allowed interval"() {
        given: 'event interval is 1000 ms'
        Step back = new Step(Speed.SLOW, Direction.BACK)

        when:
        soundListenerAction.run()
        soundListenerAction.run()

        then: 'only 1 sequence is added'
        1 * motionProcessor.pulse(back)
        0 * _
    }

    def "test run() for 2 sequential events matching interval"() {
        given: 'event interval is 0'
        SoundListenerAction soundListenerAction = new SoundListenerActionImpl(0, motionProcessor)
        Step back = new Step(Speed.SLOW, Direction.BACK)

        when:
        soundListenerAction.run()
        soundListenerAction.run()

        then: '2 sequences are added'
        2 * motionProcessor.pulse(back)
        0 * _
    }
}
