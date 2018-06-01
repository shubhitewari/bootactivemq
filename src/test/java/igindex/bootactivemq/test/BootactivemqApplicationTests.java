package igindex.bootactivemq.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import igindex.bootactivemq.jms.OrderProducer;
import igindex.bootactivemq.jms.OrderPublisher;
import igindex.bootactivemq.web.OrderFileUploader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootactivemqApplicationTests {
	@Autowired
    private OrderFileUploader uploader;
	@Autowired
	private OrderProducer producer;
	@Autowired
	private OrderPublisher publisher;
	
	@Test
	public void testContextLoading() {
		assertThat(uploader).isNotNull();
		assertThat(producer).isNotNull();
		assertThat(publisher).isNotNull();
	}
	
	

}
