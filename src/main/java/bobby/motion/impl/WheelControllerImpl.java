package bobby.motion.impl;

import lombok.RequiredArgsConstructor;
import bobby.motion.Wheel;
import bobby.motion.WheelController;

@RequiredArgsConstructor
public class WheelControllerImpl implements WheelController {

    private final Wheel rightWheel;
    private final Wheel leftWheel;

    @Override
    public void pulseForward() {
        rightWheel.forwardPulse();
        leftWheel.forwardPulse();
    }

    @Override
    public void pulseRight() {
        rightWheel.forwardPulse();
        leftWheel.backwardPulse();
    }

    @Override
    public void pulseLeft() {
        rightWheel.backwardPulse();
        leftWheel.forwardPulse();
    }

    @Override
    public void pulseBackward() {
        rightWheel.backwardPulse();
        leftWheel.backwardPulse();
    }

    @Override
    public void moveForward() {
        rightWheel.moveForward();
        leftWheel.moveForward();
    }

    @Override
    public void moveBackward() {
        rightWheel.moveBackward();
        leftWheel.moveBackward();
    }

    @Override
    public void turnRight() {
        rightWheel.moveForward();
        leftWheel.moveBackward();
    }

    @Override
    public void turnLeft() {
        rightWheel.moveBackward();
        leftWheel.moveForward();
    }

    @Override
    public void stop() {
        rightWheel.stop();
        leftWheel.stop();
    }
}
