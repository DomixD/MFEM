package muenchen.praxis.mfem.resource;

import muenchen.praxis.mfem.services.IMFEMService;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.security.access.AccessDeniedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MFEMResource.class)
@WebAppConfiguration
public class MFEMResourceTest {

    @Autowired
    private WebApplicationContext webAppContext;
    private MockMvc mockMvc;

    @MockBean
    private IMFEMService service;

    private List<Double> resultList = new ArrayList<>(Arrays.asList(1.0, 0.5, 0.0, 1.0, 0.5, 0.0));
    private List<Integer> frameList = new ArrayList<>(Arrays.asList(1, 2, 3));

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    @WithMockUser(authorities = "EVALUATION")
    public void testGetResult() throws Exception{
        when(service.getResult(anyInt(), anyInt())).thenReturn(resultList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getRes/1/1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200, result.getResponse().getStatus());
        assertEquals("[1.0,0.5,0.0,1.0,0.5,0.0]", result.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser(authorities = "READ_ACCESS")
    public void testGetResultAccessDenied() throws Exception{
        exception.expectCause(IsInstanceOf.<Throwable>instanceOf(AccessDeniedException.class));
        when(service.getResult(anyInt(), anyInt())).thenReturn(resultList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getRes/1/1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    }

    @Test
    @WithMockUser(authorities = "EVALUATION")
    public void testGetFrames() throws Exception {
        when(service.getFrames(anyInt())).thenReturn(frameList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getFrames/1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200, result.getResponse().getStatus());
        assertEquals("[1,2,3]", result.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser(authorities = "READ_ACCESS")
    public void testGetFramesAccessDenied() throws Exception {
        exception.expectCause(IsInstanceOf.<Throwable>instanceOf(AccessDeniedException.class));
        when(service.getFrames(anyInt())).thenReturn(frameList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getFrames/1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    }

}
