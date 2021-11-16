package bobby.motion.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import bobby.motion.Direction;
import bobby.motion.MotionProcessor;
import bobby.motion.WheelController;
import bobby.motion.Step;

@Slf4j
@RequiredArgsConstructor
public class MotionProcessorImpl implements MotionProcessor {

    private final WheelController wheelController;

    @Override
    public void move(Step step) {
        Direction direction = step.getDirection();
        log.info("Moving " + direction);

        switch(direction) {
            case FORWARD:
                wheelController.moveForward();
                break;
            case RIGHT:
                wheelController.turnRight();
                break;
            case LEFT:
                wheelController.turnLeft();
                break;
            case BACK:
                wheelController.moveBackward();
                break;
            case STOP:
                wheelController.stop();
                break;
            default:
                throw new IllegalArgumentException("Unsupported direction");
        }
    }

    @Override
    public void pulse(Step step) {
        Direction direction = step.getDirection();
        log.info("Pulsing " + direction);

        switch(direction) {
            case FORWARD:
                wheelController.pulseForward();
                break;
            case RIGHT:
                wheelController.pulseRight();
                break;
            case LEFT:
                wheelController.pulseLeft();
                break;
            case BACK:
                wheelController.pulseBackward();
                break;
            default:
                throw new IllegalArgumentException("Unsupported direction");
        }
    }
}
