package igindex.bootactivemq.web;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import igindex.bootactivemq.entity.Orders;
import igindex.bootactivemq.jms.OrderProducer;
import igindex.bootactivemq.jms.OrderPublisher;


@Controller
public class OrderFileUploader {
	private static final Logger logger = LoggerFactory.getLogger(OrderFileUploader.class);
	private static final String TOPIC = "topic";

	@Autowired
	OrderProducer producer;

	@Autowired
	OrderPublisher publisher;

	@GetMapping("/orderUpload")
	public String orderUpload(Model model) throws IOException {
		logger.info("Displaying Upload Page" );
		return "orderUpload";
	}

	@PostMapping(name = "/orderUpload")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("dest") String destination, 
			RedirectAttributes redirectAttributes) {
		logger.debug("Destination selected by user to send uploaded file: "  + destination);
		if (file.isEmpty()) {
			logger.info("No file selected for upload by user");
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:orderUpload";
		}
		try {
			logger.info("De-Serialising uploaded file");
			InputStream bytes = file.getInputStream();
			JAXBContext jaxbContext = JAXBContext.newInstance(Orders.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Orders orders = (Orders) jaxbUnmarshaller.unmarshal(bytes);
			logger.info("File De-Serialised Successfully");

			if(null != destination && TOPIC.equalsIgnoreCase(destination)) {
				publisher.process(orders);
			}else {
				producer.process(orders);
			}
			redirectAttributes.addFlashAttribute("message", "File pushed to Active MQ successfully");

		} catch (JAXBException | IOException e) {
			logger.error("Exception occured while deserialising file: "+ e.getCause());
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Error occured while processing file. Please try again.");
		}
		return "redirect:/uploadStatus";
	}

	@GetMapping("/uploadStatus")
	public String uploadStatus() {
		return "uploadStatus";
	}


}
