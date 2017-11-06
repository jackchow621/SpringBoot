package ghost.springboot;


import ghost.springboot.message.RabbitMQReceiver;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class RebbitMQRunner implements CommandLineRunner {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RebbitMQRunner.class);

	private final RabbitTemplate rabbitTemplate;
	private final RabbitMQReceiver receiver;
	private final ConfigurableApplicationContext context;

	public RebbitMQRunner(RabbitMQReceiver receiver, RabbitTemplate rabbitTemplate,
			ConfigurableApplicationContext context) {
		this.receiver = receiver;
		this.rabbitTemplate = rabbitTemplate;
		this.context = context;
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("Sending message...");
		rabbitTemplate.convertAndSend(Application.queueName,
				"Hello from RabbitMQ!");
		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		context.close();
	}

}
