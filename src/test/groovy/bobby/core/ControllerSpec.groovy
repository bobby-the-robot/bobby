package bobby.core

import bobby.core.raspberrypi.impl.RaspberryPiControllerImpl
import bobby.sensor.ListenerAction
import spock.lang.Specification
import spock.lang.Subject

class ControllerSpec extends Specification {

    InputFactory inputFactory = Mock InputFactory
    OutputFactory outputFactory = Mock OutputFactory
    ListenerFactory listenerFactory = Mock ListenerFactory
    ListenerAction action = Mock ListenerAction
    List<ListenerAction> inputActions = [action, action, action]

    @Subject
    Controller controller = new RaspberryPiControllerImpl(inputFactory, outputFactory,  listenerFactory, inputActions)

    Input input = Mock Input
    Output output = Mock Output
    Listener listener = Mock Listener

    def "test init()"() {
        given:
        int pin = 1

        when:
        controller.init()

        then:
        3 * listenerFactory.getInstance(action) >> listener
        3 * action.pin >> pin
        3 * inputFactory.getInstance(pin) >> input
        3 * input.addListener(listener)
        0 * _
    }

    def "test initInput()"() {
        given:
        Integer pinNumber = 1

        when:
        Input result = controller.initInput(pinNumber, action)

        then:
        1 * listenerFactory.getInstance(action) >> listener
        1 * inputFactory.getInstance(pinNumber) >> input
        1 * input.addListener(listener)
        0 * _

        and:
        result == input
    }

    def "test initOutput()"() {
        given:
        Integer pinNumber = 1

        when:
        Output result = controller.initOutput(pinNumber)

        then:
        1 * outputFactory.getInstance(pinNumber) >> output
        0 * _

        and:
        result == output
    }
}
