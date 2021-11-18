package bobby.remote.impl;

import bobby.dto.MotionDto;
import bobby.motion.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

@Slf4j
@RequiredArgsConstructor
public class WebSocketStompSessionHandlerImpl extends StompSessionHandlerAdapter {

    private static final String DESTINATION = "/client/direction";

    private final MotionProcessor motionProcessor;

    @Override
    public void afterConnected(StompSession session, StompHeaders stompHeaders) {
        log.info("Connecting to remote controller");
        session.subscribe(DESTINATION, this);
        log.info("Connected!");
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        exception.printStackTrace();
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return MotionDto.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        log.info("Received : " + payload);
        MotionDto motionDto = (MotionDto) payload;
        Direction direction = Direction.valueOf(motionDto.getDirection().name());
        Step step = new Step(Speed.AVERAGE, direction);
        motionProcessor.move(step);
    }
}
