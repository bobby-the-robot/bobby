package bobby.sensor.sound

import bobby.sensor.sound.impl.SoundListenerActionImpl
import spock.lang.Specification
import spock.lang.Subject

class SoundListenerActionSpec extends Specification {

    SoundSensorModule soundSensorModule = Mock SoundSensorModule

    @Subject
    SoundListenerAction soundListenerAction = new SoundListenerActionImpl(1, soundSensorModule)

    def "test run()"() {
        when:
        soundListenerAction.run()

        then:
        1 * soundSensorModule.registerEvent()
        0 * _
    }
}
