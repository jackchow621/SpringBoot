package ghost.springboot.recevier;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisRecevier {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisRecevier.class);

    private CountDownLatch latch;

    @Autowired
    public RedisRecevier(CountDownLatch latch) {
        this.latch = latch;
    }

    public void receiveMessage(String message) {
        LOGGER.info("Received <" + message + ">");
        latch.countDown();
    }
}
