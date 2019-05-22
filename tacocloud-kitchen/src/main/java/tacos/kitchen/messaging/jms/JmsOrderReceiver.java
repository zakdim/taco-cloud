package tacos.kitchen.messaging.jms;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import tacos.Order;
import tacos.kitchen.OrderReceiver;

@Profile("jms-template")
@Component("templateOrderReceiver")
public class JmsOrderReceiver implements OrderReceiver {

	private JmsTemplate jms;
//	private MessageConverter converter;
	
//	@Autowired
//	public JmsOrderReceiver(JmsTemplate jms, MessageConverter converter) {
//		this.jms = jms;
//		this.converter = converter;
//	}

	@Autowired
	public JmsOrderReceiver(JmsTemplate jms) {
		this.jms = jms;
	}

	@Override
	public Order receiveOrder() {
		return (Order) jms.receiveAndConvert("tacocloud.order.queue");
	}

//	public Order receiveOrder() throws MessageConversionException, JMSException {
//		Message message = jms.receive("tacocloud.order.queue");
//		return (Order) converter.fromMessage(message);
//	}
	
}