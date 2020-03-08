package bobby.core;

public interface ListenerFactory {

    Listener getInstance(Runnable action);
}
