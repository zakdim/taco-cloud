package tacos.kitchen;

import javax.jms.JMSException;

import org.springframework.jms.support.converter.MessageConversionException;

import tacos.Order;

public interface OrderReceiver {

//	Order receiveOrder() throws MessageConversionException, JMSException;
	Order receiveOrder();
}
