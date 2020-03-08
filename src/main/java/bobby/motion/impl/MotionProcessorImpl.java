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
    private final TrackController trackController;

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
                trackController.forward();
                break;
            case RIGHT:
                trackController.right();
                break;
            case LEFT:
                trackController.left();
                break;
            case BACK:
                trackController.backward();
                break;
            default:
                throw new IllegalArgumentException("Unsupported direction");
        }
    }
}
