package bobby.core

import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent
import bobby.core.raspberrypi.impl.RaspberryPiListenerImpl
import spock.lang.Specification
import spock.lang.Subject

class ListenerSpec extends Specification {

    Runnable action = Mock Runnable

    @Subject
    Listener listener = new RaspberryPiListenerImpl(action)

    GpioPinDigitalStateChangeEvent event = Mock GpioPinDigitalStateChangeEvent

    def "test handle"() {
        when:
        listener.handleGpioPinDigitalStateChangeEvent(event)

        then:
        1 * action.run()
        0 * _
    }
}
