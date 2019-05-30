package tacos.kitchen.messaging.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import tacos.Order;
import tacos.kitchen.OrderReceiver;

@Profile("rabbitmq-template")
@Component("templateOrderReceiver")
public class RabbitOrderReceiver implements OrderReceiver {

	private RabbitTemplate rabbit;
	private MessageConverter converter;
	
	@Autowired
	public RabbitOrderReceiver(RabbitTemplate rabbit) {
		this.rabbit = rabbit;
		this.converter = rabbit.getMessageConverter();
	}
	
	@Override
	public Order receiveOrder() {
		return rabbit.receiveAndConvert("tacocloud.order.queue",
				new ParameterizedTypeReference<Order>() {});
	}	
	
//	@Override
//	public Order receiveOrder() {
//		Message message = rabbit.receive("tacocloud.orders", 30_000);
//		return message != null
//				? (Order) converter.fromMessage(message)
//				: null;
//	}

//	@Override
//	public Order receiveOrder() {
//		Message message = rabbit.receive("tacocloud.orders");
//		return message != null
//				? (Order) converter.fromMessage(message)
//				: null;
//	}

}
