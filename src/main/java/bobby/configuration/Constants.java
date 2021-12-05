package bobby.configuration;

public abstract class Constants {

    public static final int RIGHT_FORWARD_WHEEL_PIN = 25;
    public static final int RIGHT_BACKWARD_WHEEL_PIN = 26;
    public static final int LEFT_FORWARD_WHEEL_PIN = 3;
    public static final int LEFT_BACKWARD_WHEEL_PIN = 4;
    public static final int DISTANCE_SENSOR_PIN = 24;
    public static final int MOTION_SENSOR_PIN = 1;
    public static final int SOUND_SENSOR_PIN = 2;

    public static final int OPERATIONS_INTERVAL = 1000;

    public static final String MOTION_CONTROL_QUEUE_NAME = "motion.control";

    public static final String AMQP_HOST = System.getenv("AMQP_HOST");
    public static final int AMQP_PORT = Integer.parseInt(System.getenv("AMQP_PORT"));
    public static final String AMQP_VHOST = System.getenv("AMQP_VHOST");
    public static final String AMQP_USER = System.getenv("AMQP_USER");
    public static final String AMQP_PASSWORD = System.getenv("AMQP_PASSWORD");

    public static final String STOMP_CONTROLLER_URL = "ws://bobby-remote-stage.herokuapp.com/client";
}
