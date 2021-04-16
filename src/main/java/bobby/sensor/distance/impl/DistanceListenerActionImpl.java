package bobby.sensor.distance.impl;

import bobby.motion.Direction;
import bobby.motion.MotionProcessor;
import bobby.motion.Speed;
import bobby.motion.Step;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import bobby.sensor.distance.DistanceListenerAction;

@Slf4j
@RequiredArgsConstructor
public class DistanceListenerActionImpl implements DistanceListenerAction {

    private final MotionProcessor motionProcessor;

    @Override
    public void run() {
        log.info(" --> Obstacle detected!");
        Step right = new Step(Speed.FAST, Direction.RIGHT);
        motionProcessor.pulse(right);
    }
}
