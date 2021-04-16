package bobby.sensor.sound.impl;

import bobby.motion.Direction;
import bobby.motion.MotionProcessor;
import bobby.motion.Speed;
import bobby.motion.Step;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import bobby.sensor.sound.SoundListenerAction;

import java.time.Instant;

@Slf4j
@RequiredArgsConstructor
public class SoundListenerActionImpl implements SoundListenerAction {

    private final int eventInterval;
    private final MotionProcessor motionProcessor;

    private Instant lastExecuted;

    @Override
    public void run() {
        log.info(" --> Sound detected!");
        Instant now = Instant.now();

        if (lastExecuted == null || now.minusMillis(eventInterval).isAfter(lastExecuted)) {
            lastExecuted = now;
            Step backward = new Step(Speed.SLOW, Direction.BACK);
            motionProcessor.pulse(backward);
        }
    }
}
