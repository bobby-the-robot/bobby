package bobby.core.raspberrypi.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import bobby.core.*;
import bobby.sensor.ListenerAction;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RaspberryPiControllerImpl implements Controller {

    private final InputFactory inputFactory;
    private final OutputFactory outputFactory;
    private final ListenerFactory listenerFactory;
    private final List<ListenerAction> inputActions;

    @Override
    public void init() {
        inputActions.stream()
                .forEach(action -> this.initInput(action.getPin(), action));
    }

    @Override
    public Input initInput(int pinNumber, Runnable action) {
        Listener listener = listenerFactory.getInstance(action);
        Input input = inputFactory.getInstance(pinNumber);
        input.addListener(listener);
        return input;
    }

    @Override
    public Output initOutput(int pinNumber) {
        return outputFactory.getInstance(pinNumber);
    }
}
