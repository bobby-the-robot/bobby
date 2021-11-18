package bobby

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import bobby.configuration.ControllerTestConfiguration

import bobby.core.Controller
import spock.lang.Specification

@SpringBootTest
@ContextConfiguration(classes = [
        BobbyApplication,
        ControllerTestConfiguration,
])
class BobbyApplicationTest extends Specification {

    @Autowired
    Controller controller

    def "test context loads"() {
        expect:
        controller != null
    }
}
