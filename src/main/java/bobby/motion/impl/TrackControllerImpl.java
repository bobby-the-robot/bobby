package bobby.motion.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import bobby.motion.Track;
import bobby.motion.TrackController;

@Component
public class TrackControllerImpl implements TrackController {

    private final Track rightTrack;
    private final Track leftTrack;

    public TrackControllerImpl(@Qualifier("rightTrack") Track rightTrack,
                               @Qualifier("leftTrack") Track leftTrack) {
        this.rightTrack = rightTrack;
        this.leftTrack = leftTrack;
    }

    @Override
    public void forward() {
        rightTrack.forward();
        leftTrack.forward();
    }

    @Override
    public void right() {
        rightTrack.forward();
        leftTrack.backward();
    }

    @Override
    public void left() {
        rightTrack.backward();
        leftTrack.forward();
    }

    @Override
    public void backward() {
        rightTrack.backward();
        leftTrack.backward();
    }
}
