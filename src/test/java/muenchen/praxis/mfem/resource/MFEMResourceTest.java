package muenchen.praxis.mfem.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import muenchen.praxis.mfem.entities.Priority;
import muenchen.praxis.mfem.entities.Question;
import muenchen.praxis.mfem.entities.Requirement;
import muenchen.praxis.mfem.services.MFEMServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MFEMResource.class, secure = false)
@WebAppConfiguration
public class MFEMResourceTest {
	
	@Autowired
	private WebApplicationContext webAppContext;
	private MockMvc mockMvc;
	
	@MockBean
	private MFEMServiceImpl service;

	private Requirement[] requirements = {new Requirement("Anforderung1", Priority.A, new ArrayList<>()),
										new Requirement("Anforderung2", Priority.B, new ArrayList<>())};

	private Question[] questions = {new Question("Frage1"),
									new Question("Frage2")};
	
	@Before
	public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }
	
	@Test
	public void testTest() throws Exception {
		when(service.testPrint()).thenReturn("Irgendwas");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/test").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}
	
	@Test
	public void testPost() throws Exception {
		when(service.doPost(any(Requirement.class))).thenReturn(anyString());
		List<Question> list = new ArrayList<Question>();
		Question q = new Question("Was?");
		list.add(q);
		Requirement r  = new Requirement("Anforderung", Priority.A, list);
		RequestBuilder request = MockMvcRequestBuilders.post("/req").content(objToJson(r)).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	public void testPostQuest() throws Exception {
		when(service.saveQuest(any(Question.class))).thenReturn(HttpStatus.OK);
		RequestBuilder request = MockMvcRequestBuilders.post("/saveQ").content(objToJson(questions[1])).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals("{\"id\":0,\"value\":\"Frage2\"}", result.getResponse().getContentAsString());
	}

	@Test
	public void testGetQuest() throws Exception {
		when(service.getQuest(0)).thenReturn(questions[0].toString());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/readQ/0").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals("Question(id=0, value=Frage1)", result.getResponse().getContentAsString());
	}

	@Test
	public void testPostReg() throws Exception {
		when(service.saveReq(any(Requirement.class))).thenReturn(HttpStatus.OK);
		RequestBuilder request = MockMvcRequestBuilders.post("/save").content(objToJson(requirements[1])).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals("{\"id\":0,\"prior\":\"B\",\"content\":\"Anforderung2\",\"questionList\":[]}", result.getResponse().getContentAsString());

	}
/*
	@Test
	public void testGetReg() throws Exception {
		when(service.getReq(0)).thenReturn(requirements[0].toString());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/read/0").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals("Requirement(id=0, prior=A, content=Anforderung1, questionList=[])", result.getResponse().getContentAsString());
	}
*/
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
