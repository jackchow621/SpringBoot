package ghost.springboot;

import ghost.springboot.message.RabbitMQReceiver;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 注释部分为使用redis实现的消息队列
 * 未注释部分为使用rabbitmq实现的消息队列
 * @author Jack
 *
 */
@SpringBootApplication
//@EnableCaching
public class Application {

//	private static final Logger LOGGER = LoggerFactory
//			.getLogger(Application.class);

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		SpringApplication.run(Application.class, args);

		/**
		 * redis
		 */
		// ApplicationContext ctx = SpringApplication.run(Application.class,
		// args);
		//
		// StringRedisTemplate template =
		// ctx.getBean(StringRedisTemplate.class);
		// CountDownLatch latch = ctx.getBean(CountDownLatch.class);
		//
		// LOGGER.info("Sending message...");
		// template.convertAndSend("chat", "Hello from Redis!");
		//
		// latch.await();
		//
		// System.exit(0);
	}

	/**
	 * redis
	 */
//	@Bean
//	Receiver receiver(CountDownLatch latch) {
//		return new Receiver(latch);
//	}
//
//	@Bean
//	CountDownLatch latch() {
//		return new CountDownLatch(1);
//	}
//
//	@Bean
//	StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
//		return new StringRedisTemplate(connectionFactory);
//	}
//
//	@Bean
//	RedisMessageListenerContainer container(
//			RedisConnectionFactory connectionFactory,
//			MessageListenerAdapter listenerAdapter) {
//
//		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//		container.setConnectionFactory(connectionFactory);
//		container.addMessageListener(listenerAdapter, new PatternTopic("chat"));
//
//		return container;
//	}
//
//	@Bean
//	MessageListenerAdapter listenerAdapter(Receiver receiver) {
//		return new MessageListenerAdapter(receiver, "receiveMessage");
//	}
	
	// ------------------------------------------------------------------------------------------------------------------------

	/**
	 * rabbitmq
	 */
	final static String queueName = "spring-boot";

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("spring-boot-exchange");
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(queueName);
	}
	
	//rabbitmq的配置
	@Bean  
    public ConnectionFactory connectionFactory() {  
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();  
        connectionFactory.setAddresses("192.168.1.130:5672");  
        connectionFactory.setUsername("admin");  
        connectionFactory.setPassword("admin");  
        connectionFactory.setVirtualHost("/");  
        connectionFactory.setPublisherConfirms(true); //必须要设置  
        return connectionFactory;  
    }

	@Bean
	SimpleMessageListenerContainer container(
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		
		container.setConnectionFactory(connectionFactory());
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}
	
	@Bean
	MessageListenerAdapter listenerAdapter(RabbitMQReceiver rabbitMQReceiver) {
		return new MessageListenerAdapter(rabbitMQReceiver, "receiveMessage");
	}

}
