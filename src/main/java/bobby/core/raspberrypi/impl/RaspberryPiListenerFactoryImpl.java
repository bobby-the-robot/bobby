package bobby.core.raspberrypi.impl;

import org.springframework.stereotype.Component;
import bobby.core.Listener;
import bobby.core.ListenerFactory;

@Component
public class RaspberryPiListenerFactoryImpl implements ListenerFactory {

    @Override
    public Listener getInstance(Runnable action) {
        return new RaspberryPiListenerImpl(action);
    }
}
