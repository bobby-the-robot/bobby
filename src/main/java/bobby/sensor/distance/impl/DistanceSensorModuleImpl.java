package bobby.sensor.distance.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import bobby.motion.Direction;
import bobby.motion.Route;
import bobby.motion.Speed;
import bobby.motion.Step;
import bobby.sensor.distance.DistanceSensorModule;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DistanceSensorModuleImpl implements DistanceSensorModule {

    private final Route route;

    @Override
    public void registerEvent() {
        Step right = new Step(Speed.FAST, Direction.RIGHT);
        List<Step> sequence = List.of(right);
        route.addSequence(sequence);
    }
}
