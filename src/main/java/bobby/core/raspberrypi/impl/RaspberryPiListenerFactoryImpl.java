package bobby.core.raspberrypi.impl;

import bobby.core.Listener;
import bobby.core.ListenerFactory;

public class RaspberryPiListenerFactoryImpl implements ListenerFactory {

    @Override
    public Listener getInstance(Runnable action) {
        return new RaspberryPiListenerImpl(action);
    }
}
