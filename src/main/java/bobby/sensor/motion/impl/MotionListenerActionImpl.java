package bobby.sensor.motion.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import bobby.sensor.motion.MotionListenerAction;
import bobby.sensor.motion.MotionSensorModule;

@Slf4j
@Component
public class MotionListenerActionImpl implements MotionListenerAction {

    @Getter
    private final int pin;
    private final MotionSensorModule motionSensorModule;

    public MotionListenerActionImpl(@Value("${sensor.motion.pin}") int pin,
                                    MotionSensorModule motionSensorModule) {
        this.pin = pin;
        this.motionSensorModule = motionSensorModule;
    }

    @Override
    public void run() {
        log.info(" --> Motion detected!");
        motionSensorModule.registerEvent();
    }
}
