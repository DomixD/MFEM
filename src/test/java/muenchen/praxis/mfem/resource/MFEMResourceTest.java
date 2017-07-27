package muenchen.praxis.mfem.resource;

import static org.junit.Assert.*;
import muenchen.praxis.mfem.services.MFEMServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)
@WebMvcTest(value = MFEMResource.class, secure = false)
public class MFEMResourceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MFEMServiceImpl service;
	
	@Test
	public void testTest() throws Exception {
		Mockito.when(service.testPrint()).thenReturn("Irgendwas");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/test").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}
}
