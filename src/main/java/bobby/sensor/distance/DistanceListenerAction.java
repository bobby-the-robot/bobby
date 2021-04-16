package bobby.sensor.distance;

import bobby.motion.Direction;
import bobby.motion.MotionProcessor;
import bobby.motion.Speed;
import bobby.motion.Step;
import bobby.sensor.ListenerAction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DistanceListenerAction implements ListenerAction {

    private final MotionProcessor motionProcessor;

    @Override
    public void run() {
        log.info(" --> Obstacle detected!");
        Step right = new Step(Speed.FAST, Direction.RIGHT);
        motionProcessor.pulse(right);
    }
}
