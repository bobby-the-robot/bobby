package bobby

import org.springframework.boot.ApplicationArguments
import bobby.core.Controller
import spock.lang.Specification
import spock.lang.Subject

class BobbyAppSpec extends Specification {

    Controller controller = Mock Controller

    @Subject
    BobbyApplication bobbyApplication = new BobbyApplication(controller)

    def "test run()"() {
        when:
        bobbyApplication.run(_ as ApplicationArguments)

        then:
        1 * controller.init()
        0 * _
    }
}
