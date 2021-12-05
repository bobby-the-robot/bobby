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

    public static final String AMQP_HOST = System.getProperty("amqp_host");
    public static final int AMQP_PORT = 5672;
    public static final String AMQP_VHOST = System.getProperty("amqp_vhost");
    public static final String AMQP_USER = System.getProperty("amqp_user");
    public static final String AMQP_PASSWORD = System.getProperty("amqp_password");

    public static final String STOMP_CONTROLLER_URL = "ws://bobby-remote-stage.herokuapp.com/client";
}
