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
    public void forward() {
        rightWheel.forward();
        leftWheel.forward();
    }

    @Override
    public void right() {
        rightWheel.forward();
        leftWheel.backward();
    }

    @Override
    public void left() {
        rightWheel.backward();
        leftWheel.forward();
    }

    @Override
    public void backward() {
        rightWheel.backward();
        leftWheel.backward();
    }
}
