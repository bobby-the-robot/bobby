package bobby.sensor.motion

import bobby.sensor.motion.impl.MotionListenerActionImpl
import spock.lang.Specification
import spock.lang.Subject

class MotionListenerActionSpec extends Specification {

    MotionSensorModule sensorModule = Mock MotionSensorModule

    @Subject
    MotionListenerAction motionListenerAction = new MotionListenerActionImpl(1, sensorModule)

    def "test run()"() {
        when:
        motionListenerAction.run()

        then:
        1 * sensorModule.registerEvent()
        0 * _
    }
}
