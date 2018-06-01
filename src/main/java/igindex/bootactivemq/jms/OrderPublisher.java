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
public class OrderPublisher {
	private static final Logger logger = LoggerFactory.getLogger(OrderPublisher.class);
	
	@Autowired
	JmsTemplate jmsTemplatePubSub;
	
	@Value("${igtest.order.topic}")
	String orderT;

	public void process(Orders orders) {
		logger.info("Processing Orders for Topic now...:" );
		for(Order order : orders.getOrders())
	    {
			logger.info("Publishing order: " + order  + "to Topic on Active MQ");
			publish(order);
			logger.info("Order published successfully");
	    }
	}
	
	public void publish(Order order){
		jmsTemplatePubSub.setPubSubDomain(true);
		jmsTemplatePubSub.convertAndSend(orderT,order);
	}

}
