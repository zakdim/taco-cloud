package tacos.messaging;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Destination;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import tacos.Order;

@Configuration
public class MessagingConfig {

	@Bean
	public Destination orderQueue() {
		return new ActiveMQQueue("tacocloud.order.queue");
	}

	@Bean
	public MappingJackson2MessageConverter messageConverter() {
		MappingJackson2MessageConverter messageConverter = 
				new MappingJackson2MessageConverter();

		messageConverter.setTypeIdPropertyName("_typeId");

		Map<String, Class<?>> typeIdMappings = 
				new HashMap<String, Class<?>>();
		typeIdMappings.put("order", Order.class);
		messageConverter.setTypeIdMappings(typeIdMappings);

		return messageConverter;
	}
}
