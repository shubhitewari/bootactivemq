package igindex.bootactivemq.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import igindex.bootactivemq.entity.Order;
import igindex.bootactivemq.entity.Orders;

@Component
public class OrderProducer {
	private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);

	@Autowired
	JmsTemplate jmsTemplate;

	@Value("${igtest.order.queue}")
	String orderQ;

	public void process(Orders orders) {
		logger.info("Processing Orders for Queue now...:" );
		for(Order order : orders.getOrders())
		{
			logger.info("Sending order: " + order  + "to Queue on Active MQ");
			send(order);
			logger.info("Order sent successfully");
		}
	}

	public void send(Order order){
		jmsTemplate.convertAndSend(orderQ,order);
	}

}
