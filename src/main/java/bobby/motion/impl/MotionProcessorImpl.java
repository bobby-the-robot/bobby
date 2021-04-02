package bobby.motion.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import bobby.motion.*;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MotionProcessorImpl implements MotionProcessor {

    private final Route route;
    private final WheelController wheelController;

    @Override
    public void process() {
        List<Step> sequence = route.nextSequence();

        sequence.stream()
                .forEach(this::move);
    }

    private void move(Step step) {
        if (step == null) {
            return;
        }

        Direction direction = step.getDirection();
        log.info("Moving " + direction);

        switch(direction) {
            case FORWARD:
                wheelController.forward();
                break;
            case RIGHT:
                wheelController.right();
                break;
            case LEFT:
                wheelController.left();
                break;
            case BACK:
                wheelController.backward();
                break;
            default:
                throw new IllegalArgumentException("Unsupported direction");
        }
    }
}
