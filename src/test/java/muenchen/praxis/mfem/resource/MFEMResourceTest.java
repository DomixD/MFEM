package muenchen.praxis.mfem.resource;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import muenchen.praxis.mfem.entities.Priority;
import muenchen.praxis.mfem.entities.Question;
import muenchen.praxis.mfem.entities.Requirement;
import muenchen.praxis.mfem.services.MFEMServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


@RunWith(SpringRunner.class)
@WebMvcTest(value = MFEMResource.class, secure = false)
@WebAppConfiguration
public class MFEMResourceTest {
	
	@Autowired
	private WebApplicationContext webAppContext;
	private MockMvc mockMvc;
	
	@MockBean
	private MFEMServiceImpl service;
	
	@Before
	public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }
	
	@Test
	public void testTest() throws Exception {
		
		Mockito.when(service.testPrint()).thenReturn("Irgendwas");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/test").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		
	}
	
	@Test
	public void testPost() throws Exception {
		Mockito.when(service.doPost(Mockito.any(Requirement.class))).thenReturn("");
		List<Question> list = new ArrayList<Question>();
		Question q = new Question("Was?");
		list.add(q);
		Requirement r  = new Requirement("Anforderung", Priority.A, list);
		RequestBuilder request = MockMvcRequestBuilders.post("/req").content(objToJson(r)).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

    private String objToJson(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        String result = "";
        try {
            result = mapper.writeValueAsString(o);
            System.out.println(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
