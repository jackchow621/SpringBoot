package ghost.springboot.runner;

import ghost.springboot.Application;
import ghost.springboot.recevicer.RabbitMQReceiver;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RabbitMQRunner implements CommandLineRunner{
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RabbitMQRunner.class);

	private final RabbitTemplate rabbitTemplate;
    private final RabbitMQReceiver rabbitMQReceiver;
    private final ConfigurableApplicationContext context;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    public RabbitMQRunner(RabbitMQReceiver rabbitMQReceiver, RabbitTemplate rabbitTemplate,
            ConfigurableApplicationContext context) {
        this.rabbitMQReceiver = rabbitMQReceiver;
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
    }

    @Override
    public void run(String... args) throws Exception {
    	// TODO Auto-generated method stub
        LOGGER.info("Sending message...");
        rabbitTemplate.convertAndSend(Application.queueName, "Hello from RabbitMQ!");
        rabbitMQReceiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        context.close();
        
        String quote = restTemplate.getForObject(
                "http://gturnquist-quoters.cfapps.io/api/random", String.class);
        LOGGER.info(quote.toString());
    }

}
