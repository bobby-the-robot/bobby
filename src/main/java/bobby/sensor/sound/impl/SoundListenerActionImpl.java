package bobby.sensor.sound.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import bobby.sensor.sound.SoundListenerAction;
import bobby.sensor.sound.SoundSensorModule;

@Slf4j
@Component
public class SoundListenerActionImpl implements SoundListenerAction {

    @Getter
    private final int pin;
    private final SoundSensorModule soundSensorModule;

    public SoundListenerActionImpl(@Value("${sensor.sound.pin}") int pin,
                                   SoundSensorModule soundSensorModule) {
        this.pin = pin;
        this.soundSensorModule = soundSensorModule;
    }

    @Override
    public void run() {
        log.info(" --> Sound detected!");
        soundSensorModule.registerEvent();
    }
}
