package bobby.remote.impl;

import bobby.remote.MessageReceiver;
import bobby.motion.MotionProcessor;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import static bobby.configuration.Constants.*;

@Slf4j
@RequiredArgsConstructor
public class MessageReceiverImpl implements MessageReceiver {

    private final MotionProcessor motionProcessor;

    @Override
    @SneakyThrows
    public void run() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(AMQP_HOST);
        factory.setPort(AMQP_PORT);
        factory.setVirtualHost(AMQP_VHOST);
        factory.setUsername(AMQP_USER);
        factory.setPassword(AMQP_PASSWORD);

        log.info(AMQP_HOST);
        log.info(String.valueOf(AMQP_PORT));
        log.info(AMQP_VHOST);
        log.info(AMQP_USER);
        log.info(AMQP_PASSWORD);

        Connection connection = factory.newConnection();

        try (Channel channel = connection.createChannel()) {
            channel.queueDeclare(MOTION_CONTROL_QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            };

            while (true) {
                channel.basicConsume(MOTION_CONTROL_QUEUE_NAME, true, deliverCallback, consumerTag -> { });
            }
        }
    }
}