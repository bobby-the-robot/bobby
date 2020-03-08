package bobby.sensor.distance

import bobby.motion.Direction
import bobby.motion.Route
import bobby.motion.Speed
import bobby.motion.Step
import bobby.sensor.distance.impl.DistanceSensorModuleImpl
import spock.lang.Specification
import spock.lang.Subject

class DistanceSensorModuleSpec extends Specification {

    Route route = Mock Route

    @Subject
    DistanceSensorModule sensorModule = new DistanceSensorModuleImpl(route)

    def "test registerEvent()"() {
        given:
        List<Step> sequence = [new Step(Speed.FAST, Direction.RIGHT)]

        when:
        sensorModule.registerEvent()

        then:
        1 * route.addSequence(sequence)
        0 * _
    }
}
