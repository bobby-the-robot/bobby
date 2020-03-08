package bobby.motion

import bobby.core.Output
import bobby.motion.impl.TrackImpl
import spock.lang.Specification
import spock.lang.Subject

class TrackSpec extends Specification {

    Output forwardOutput = Mock Output
    Output backwardOutput = Mock Output

    @Subject
    Track track = new TrackImpl(forwardOutput, backwardOutput)

    def "test forward()"() {
        when:
        track.forward()

        then:
        1 * forwardOutput.pulse()
        0 * _
    }

    def "test backward()"() {
        when:
        track.backward()

        then:
        1 * backwardOutput.pulse()
        0 * _
    }
}
