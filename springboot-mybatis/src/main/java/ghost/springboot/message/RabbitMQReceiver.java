package ghost.springboot.message;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQReceiver {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RabbitMQReceiver.class);

	private CountDownLatch latch = new CountDownLatch(1);

	public void receiveMessage(String message) {
		LOGGER.info("Received <" + message + ">");
		latch.countDown();
	}

	public CountDownLatch getLatch() {
		return latch;
	}
}
