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
    public void forwardPulse() {
        rightWheel.forwardPulse();
        leftWheel.forwardPulse();
    }

    @Override
    public void rightPulse() {
        rightWheel.forwardPulse();
        leftWheel.backwardPulse();
    }

    @Override
    public void leftPulse() {
        rightWheel.backwardPulse();
        leftWheel.forwardPulse();
    }

    @Override
    public void backwardPulse() {
        rightWheel.backwardPulse();
        leftWheel.backwardPulse();
    }
}
