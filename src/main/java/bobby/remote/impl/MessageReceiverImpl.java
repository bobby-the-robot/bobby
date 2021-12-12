package bobby.remote.impl;

import bobby.motion.Direction;
import bobby.motion.Speed;
import bobby.motion.Step;
import bobby.remote.MessageReceiver;
import bobby.motion.MotionProcessor;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import static bobby.configuration.Constants.AMQP_URI;
import static bobby.configuration.Constants.MOTION_CONTROL_QUEUE_NAME;

@Slf4j
@RequiredArgsConstructor
public class MessageReceiverImpl implements MessageReceiver {

    private final MotionProcessor motionProcessor;

    @Override
    @SneakyThrows
    public void run() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(AMQP_URI);

        Channel channel = factory.newConnection()
                .createChannel();
        channel.basicQos(1);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            Direction direction = Direction.valueOf(message);
            Step step = new Step(Speed.AVERAGE, direction);
            motionProcessor.move(step);
        };

        log.info("Ready to process commands");
        channel.basicConsume(MOTION_CONTROL_QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
