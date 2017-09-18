package muenchen.praxis.mfem.resource;

import muenchen.praxis.mfem.services.IMFEMService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MFEMResource.class)
public class MFEMResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IMFEMService service;

    private static List<Double> resultsList = new ArrayList<>();
    private static List<Integer> resultsList2 = new ArrayList<>();

    @BeforeClass
    public static void setUp() {
        resultsList.add(1.2);
        resultsList.add(0.8);
        resultsList.add(1.5);
        resultsList2.add(1);
        resultsList2.add(3);
        resultsList2.add(4);
    }

    @Test
    public void testGetResult() throws Exception {
        when(service.getResult(1, 1)).thenReturn(resultsList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getRes/1/1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200, result.getResponse().getStatus());
        assertEquals("[1.2,0.8,1.5]", result.getResponse().getContentAsString());
    }

    @Test
    public void testGetFrames () throws Exception {
        when(service.getFrames(1)).thenReturn(resultsList2);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getFrames/1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200, result.getResponse().getStatus());
        assertEquals("[1,3,4]", result.getResponse().getContentAsString());
    }
}
