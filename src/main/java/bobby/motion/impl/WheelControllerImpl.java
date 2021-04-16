package bobby.motion.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import bobby.motion.Wheel;
import bobby.motion.WheelController;

@Component
public class WheelControllerImpl implements WheelController {

    private final Wheel rightWheel;
    private final Wheel leftWheel;

    public WheelControllerImpl(@Qualifier("rightWheel") Wheel rightWheel,
                               @Qualifier("leftWheel") Wheel leftWheel) {
        this.rightWheel = rightWheel;
        this.leftWheel = leftWheel;
    }

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
