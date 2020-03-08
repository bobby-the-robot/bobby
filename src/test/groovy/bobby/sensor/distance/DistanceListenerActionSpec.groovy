package bobby.sensor.distance

import bobby.sensor.distance.impl.DistanceListenerActionImpl
import spock.lang.Specification
import spock.lang.Subject

class DistanceListenerActionSpec extends Specification {

    DistanceSensorModule sensorModule = Mock DistanceSensorModule

    @Subject
    DistanceListenerAction distanceListenerAction = new DistanceListenerActionImpl(1, sensorModule)

    def "test run()"() {
        when:
        distanceListenerAction.run()

        then:
        1 * sensorModule.registerEvent()
        0 * _
    }
}
