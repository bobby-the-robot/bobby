package bobby.remote

import bobby.motion.Direction
import bobby.motion.MotionProcessor
import bobby.motion.Speed
import bobby.motion.Step
import bobby.dto.MotionDto

import bobby.remote.impl.WebSocketStompSessionHandlerImpl
import org.springframework.messaging.simp.stomp.StompHeaders
import org.springframework.messaging.simp.stomp.StompSession
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter
import spock.lang.Specification
import spock.lang.Subject

import java.lang.reflect.Type

class WebSocketStompSessionHandlerSpec extends Specification {

    MotionProcessor motionProcessor = Mock MotionProcessor

    @Subject
    StompSessionHandlerAdapter sessionHandler = new WebSocketStompSessionHandlerImpl(motionProcessor)

    StompSession session = Mock StompSession
    StompHeaders stompHeaders = Mock StompHeaders

    def "test afterConnected()"() {
        when:
        sessionHandler.afterConnected(session, stompHeaders)

        then:
        1 * session.subscribe("/client/direction", sessionHandler)
        0 * _
    }

    def "test getPayloadType()"() {
        when:
        Type result = sessionHandler.getPayloadType(stompHeaders)

        then:
        result == MotionDto
    }

    def "test handleFrame()"() {
        given:
        Object payload = new MotionDto()
        payload.direction = MotionDto.Direction.FORWARD
        Step step = new Step(Speed.AVERAGE, Direction.FORWARD)

        when:
        sessionHandler.handleFrame(stompHeaders, payload)

        then:
        1 * motionProcessor.move(step)
        0 * _
    }
}
