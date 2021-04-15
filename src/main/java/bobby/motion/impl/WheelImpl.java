package bobby.motion.impl;

import lombok.RequiredArgsConstructor;
import bobby.core.Output;
import bobby.motion.Wheel;

@RequiredArgsConstructor
public class WheelImpl implements Wheel {

    private final Output forwardOutput;
    private final Output backwardOutput;

    @Override
    public void forwardPulse() {
        forwardOutput.pulse();
    }

    @Override
    public void backwardPulse() {
        backwardOutput.pulse();
    }
}
