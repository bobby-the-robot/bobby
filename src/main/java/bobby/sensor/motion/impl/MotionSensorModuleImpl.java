package bobby.sensor.motion.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import bobby.motion.Direction;
import bobby.motion.Route;
import bobby.motion.Speed;
import bobby.motion.Step;
import bobby.sensor.motion.MotionSensorModule;

import java.time.Instant;
import java.util.List;

@Slf4j
@Component
public class MotionSensorModuleImpl implements MotionSensorModule {

    private final int eventInterval;
    private final Route route;

    private Instant lastExecuted;

    public MotionSensorModuleImpl(
            @Value("${sensor.motion.interval}") int eventInterval,
            Route route) {
        this.eventInterval = eventInterval;
        this.route = route;
    }

    @Override
    public void registerEvent() {
        Instant now = Instant.now();

        if (lastExecuted == null || now.minusMillis(eventInterval).isAfter(lastExecuted)) {
            lastExecuted = now;
            Step forward = new Step(Speed.AVERAGE, Direction.FORWARD);
            List<Step> sequence = List.of(forward);
            route.addSequence(sequence);
        }
    }
}
