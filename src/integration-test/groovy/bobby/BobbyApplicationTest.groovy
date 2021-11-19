package bobby

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import bobby.configuration.ControllerTestConfiguration

import spock.lang.Specification

@SpringBootTest
@ContextConfiguration(classes = [
        BobbyApplication,
        ControllerTestConfiguration,
])
@ActiveProfiles("integrationTest")
class BobbyApplicationTest extends Specification {

    @Autowired
    ApplicationContext applicationContext

    def "test context loads"() {
        expect:
        applicationContext != null
    }
}
