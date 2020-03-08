package bobby.sensor.distance.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import bobby.sensor.distance.DistanceListenerAction;
import bobby.sensor.distance.DistanceSensorModule;

@Slf4j
@Component
public class DistanceListenerActionImpl implements DistanceListenerAction {

    @Getter
    private final int pin;
    private final DistanceSensorModule distanceSensorModule;

    public DistanceListenerActionImpl(@Value("${sensor.distance.pin}") int pin,
                                      DistanceSensorModule distanceSensorModule) {
        this.pin = pin;
        this.distanceSensorModule = distanceSensorModule;
    }

    @Override
    public void run() {
        log.info(" --> Obstacle detected!");
        distanceSensorModule.registerEvent();
    }
}
