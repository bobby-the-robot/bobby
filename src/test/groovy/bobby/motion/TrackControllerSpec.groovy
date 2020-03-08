package bobby.motion

import bobby.motion.impl.TrackControllerImpl
import spock.lang.Specification
import spock.lang.Subject

class TrackControllerSpec extends Specification {

    Track rightTrack = Mock Track
    Track leftTrack = Mock Track

    @Subject
    TrackController trackController = new TrackControllerImpl(rightTrack, leftTrack)

    def "test forward()"() {
        when:
        trackController.forward()

        then:
        1 * rightTrack.forward()
        1 * leftTrack.forward()
        0 * _
    }

    def "test right()"() {
        when:
        trackController.right()

        then:
        1 * rightTrack.forward()
        1 * leftTrack.backward()
        0 * _
    }

    def "test left()"() {
        when:
        trackController.left()

        then:
        1 * rightTrack.backward()
        1 * leftTrack.forward()
        0 * _
    }

    def "test backward()"() {
        when:
        trackController.backward()

        then:
        1 * rightTrack.backward()
        1 * leftTrack.backward()
        0 * _
    }
}
