package bobby.motion

import bobby.motion.impl.RouteImpl
import spock.lang.Specification
import spock.lang.Subject

class RouteSpec extends Specification {

    @Subject
    Route route = new RouteImpl()

    def "test addSteps()"() {
        given:
        Step step1 = new Step(Speed.AVERAGE, Direction.FORWARD)
        Step step2 = new Step(Speed.SLOW, Direction.RIGHT)
        Step step3 = new Step(Speed.FAST, Direction.LEFT)
        Step step4 = new Step(Speed.AVERAGE, Direction.BACK)
        Step step5 = new Step(Speed.FAST, Direction.FORWARD)
        List<Step> sequence1 = List.of(step1, step2, step3)
        List<Step> sequence2 = List.of(step4, step5)

        when:
        route.addSequence(sequence1)
        route.addSequence(sequence2)

        then:
        route.nextSequence() == sequence2
        route.nextSequence() == sequence1
        route.nextSequence() == []
    }
}
