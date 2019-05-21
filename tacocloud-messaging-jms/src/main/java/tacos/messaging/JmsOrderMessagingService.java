package tacos.messaging;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import tacos.Order;

@Service
public class JmsOrderMessagingService implements OrderMessagingService {

	private JmsTemplate jms;	
	
	private Destination orderQueue;
	
	@Autowired
	public JmsOrderMessagingService(JmsTemplate jms,
			Destination orderQueue) {
		this.jms = jms;
		this.orderQueue = orderQueue;
	}
	
	@Override
	public void sendOrder(Order order) {
		jms.convertAndSend("tacocloud.order.queue", order,
				this::addOrderSource);
		
	}
	
	private Message addOrderSource(Message message) throws JMSException {
		message.setStringProperty("X_ORDER_SOURCE", "WEB");
		return message;
	}
	
	//
	// Different variations from section 8.1 - Sending messages with JMS
	//
	
//	public void sendOrder(Order order) {
//		jms.send(new MessageCreator() {			
//			@Override
//			public Message createMessage(Session session) throws JMSException {
//				return session.createObjectMessage(order);
//			}
//		});
//	}

//	public void sendOrder(Order order) {
//		jms.send(session ->  session.createObjectMessage(order));
//	}	

//	public void sendOrder(Order order) {
//		jms.send(
//				orderQueue,
//				session ->  session.createObjectMessage(order));
//	}	
	
//	public void sendOrder(Order order) {
//		jms.send(
//				"tacocloud.order.queue",
//				session ->  session.createObjectMessage(order));
//	}	
	
//	public void sendOrder(Order order) {
//		jms.convertAndSend(
//				"tacocloud.order.queue", order);
//	}	

//	public void sendOrder(Order order) {
//		jms.send(
//				"tacocloud.order.queue",
//				session -> { 
//					Message message = session.createObjectMessage(order);
//					message.setStringProperty("X_ORDER_SOURCE", "WEB");
//					return message;
//				});
//	}		
	
//	public void sendOrder(Order order) {
//		jms.convertAndSend("tacocloud.order.queue", order, 
//				new MessagePostProcessor() {
//					@Override
//					public Message postProcessMessage(Message message) throws JMSException {
//						message.setStringProperty("X_ORDER_SOURCE", "WEB");
//						return message;
//					}
//				});
//	}	
	
//	public void sendOrder(Order order) {
//		jms.convertAndSend("tacocloud.order.queue", order, 
//				message -> {
//					message.setStringProperty("X_ORDER_SOURCE", "WEB");
//					return message;
//				});
//	}

	// Example on page 188
//	@GetMapping("/convertAndSend/order")
//	public String convertAndSendOrder() {
//		Order order = buildOrder();
//		jms.convertAndSend("tacocloud.order.queue", order,
//				this::addOrderSource);
//		return "Convert and sent order";
//	}
	
}
