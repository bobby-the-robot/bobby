package bobby.motion.impl;

import org.springframework.stereotype.Component;
import bobby.motion.Route;
import bobby.motion.Step;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

@Component
public class RouteImpl implements Route {

    private final Stack<List<Step>> sequences = new Stack<>();


    @Override
    public void addSequence(List<Step> sequence) {
        this.sequences.add(sequence);
    }

    @Override
    public List<Step> nextSequence() {
        try {
            return sequences.pop();
        } catch(EmptyStackException e) {
            return List.of();
        }
    }
}
