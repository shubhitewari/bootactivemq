package igindex.bootactivemq.test;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class OrderFileUploaderTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testUploadPage() throws Exception {
		this.mvc.perform(get("/orderUpload")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("Upload File")));

	}

	@Test
	public void testStatusPage() throws Exception {
		this.mvc.perform(get("/uploadStatus")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("Upload File Status")));

	}


	@Test
	public void testHandleFileUpload() throws Exception {		
		File file= new File("F:/interview-test-orders-1.xml");
		FileInputStream fis = new FileInputStream(file);
		MockMultipartFile mockMultipartFile = new MockMultipartFile("file",file.getName(),
				"multipart/form-data", fis);
		MockHttpServletRequestBuilder builder =
				MockMvcRequestBuilders.multipart("/orderUpload")
				.file(mockMultipartFile);
		this.mvc.perform(builder).andExpect(status().isOk());

	}

}
